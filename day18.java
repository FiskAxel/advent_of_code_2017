import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day18 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input18.txt");
        Scanner reader = new Scanner(in);
		ArrayList<String> inst = new ArrayList<String>();
		while(reader.hasNextLine()) {
			inst.add(reader.nextLine());
		}
        reader.close();

		HashMap<String, Long> regs = new HashMap<String, Long>();
		for (int i = 0; i < inst.size(); i++) {
			String[]inp = inst.get(i).split(" ");
		 	regs.put(inp[1], Long.valueOf(0));
		}

		Long played = Long.valueOf(0);
		for (int i = 0; i < inst.size(); i++) {
			String[] inp = inst.get(i).split(" ");
			if (inp[0].equals("snd")) {
				played = regs.get(inp[1]);
			}
			else if (inp[0].equals("set")) {
				set(inp, regs);	
			}
			else if (inp[0].equals("add")) {
				add(inp, regs);
			}
			else if (inp[0].equals("mul")) {
				mul(inp, regs);
			}
			else if (inp[0].equals("mod")) {
				mod(inp, regs);
			}
			else if (inp[0].equals("rcv") && regs.get(inp[1]) > 0) {
				System.out.println("Part 1: " + played);
				break;
			}
			else if (inp[0].equals("jgz") && regs.get(inp[1]) > 0) {
				i += Integer.parseInt(inp[2]) - 1;
			}
		}





		//
		// PART 2
		//

		HashMap<String, Long> regs0 = new HashMap<String, Long>();
		HashMap<String, Long> regs1 = new HashMap<String, Long>();
		for (int i = 0; i < inst.size(); i++) {
			String[]inp = inst.get(i).split(" ");
		 	regs0.put(inp[1], Long.valueOf(0));
		 	regs1.put(inp[1], Long.valueOf(0));
		}
		regs0.put("p", Long.valueOf(0));
		regs1.put("p", Long.valueOf(1));
		regs0.put("1", Long.valueOf(1));
		regs1.put("1", Long.valueOf(1));
		
		int result = 0;
		int i0 = 0;
		int i1 = 0;
		ArrayList<Long> que0 = new ArrayList<Long>();
		ArrayList<Long> que1 = new ArrayList<Long>();
		while (true) {
			boolean deadlock0 = false;
			boolean deadlock1 = false;
			
			// PROGRAM 0
			String[] inp0 = inst.get(i0).split(" ");
			if (inp0[0].equals("snd")) {
				send(que1, inp0[1], regs0);
			}
			else if (inp0[0].equals("set")) {
				set(inp0, regs0);		
			}
			else if (inp0[0].equals("add")) {
				add(inp0, regs0);
			}
			else if (inp0[0].equals("mul")) {
				mul(inp0, regs0);
			}
			else if (inp0[0].equals("mod")) {
				mod(inp0, regs0);
			}
			else if (inp0[0].equals("rcv")) {
				if (que0.size() == 0) {
					deadlock0 = true;
					i0--;
				}
				else {
					regs0.put(inp0[1], que0.get(0));
					que0.remove(0);
					deadlock0 = false;
				}
			}
			else if (inp0[0].equals("jgz")  && regs0.get(inp0[1]) > 0) {
				i0 += jump(inp0[2], regs0);
			}
			i0++;


			// PROGRAM 1
			String[] inp1 = inst.get(i1).split(" ");
			if (inp1[0].equals("snd")) {
				result++;
				send(que0, inp1[1], regs1);
			}
			else if (inp1[0].equals("set")) {
				set(inp1, regs1);		
			}
			else if (inp1[0].equals("add")) {
				add(inp1, regs1);
			}
			else if (inp1[0].equals("mul")) {
				mul(inp1, regs1);
			}
			else if (inp1[0].equals("mod")) {
				mod(inp1, regs1);
			}
			else if (inp1[0].equals("rcv")) {
				if (que1.size() == 0) {
					deadlock1 = true;
					i1--;
				}
				else {
					regs1.put(inp1[1], que1.get(0));
					que1.remove(0);
					deadlock1 = false;
				}
			}
			else if (inp1[0].equals("jgz") && regs1.get(inp1[1]) > 0) {
				i1 += jump(inp1[2], regs1);
			}
			i1++;


			if (deadlock0 && deadlock1){
				break;
			}
		}
        System.out.println("Part 2: " + result);
    }
	
	public static int jump(String val, HashMap<String, Long> regs) {
		if (isNum(val) && Integer.parseInt(val) > 0) {
			return Integer.parseInt(val) - 1;
		}
		else if (val.charAt(0) == '-') {
			return - Integer.parseInt(val.substring(1)) - 1;
		}
		else if (regs.get(val).intValue() == 0) {
			return 0;
		}
		return (regs.get(val)).intValue() - 1;
	}
	public static void send(ArrayList<Long> que, String val, HashMap<String, Long> regs) {
		if (isNum(val)) {
			que.add(Long.valueOf(val));
		}
		else if (val.charAt(0) == '-') {
			que.add(-Long.valueOf(val.substring(1)) - Long.valueOf(1));
		}
		else {
			que.add(regs.get(val));
		}	
	}
	public static void set(String[] inp, HashMap<String, Long> regs) {
		if (isNum(inp[2])) {
			regs.put(inp[1], Long.parseLong(inp[2]));
		}
		else {
			regs.put(inp[1], regs.get(inp[2]));
		}
	}
	public static void add(String[] inp, HashMap<String, Long> regs) {
		Long newValue = Long.valueOf(0);
		if (isNum(inp[2])) {
			newValue = regs.get(inp[1]) + Long.parseLong(inp[2]);
		}
		else {
			newValue = regs.get(inp[1]) + regs.get(inp[2]); 
		}
		regs.put(inp[1], newValue);
	}
	public static void mul(String[] inp, HashMap<String, Long> regs) {
		Long newValue = Long.valueOf(0);
		if (isNum(inp[2])) {
			newValue = regs.get(inp[1]) * Long.parseLong(inp[2]);
		}
		else {
			newValue = regs.get(inp[1]) * regs.get(inp[2]); 
		}
		regs.put(inp[1], newValue);
	}
	public static void mod(String[] inp, HashMap<String, Long> regs) {
		Long newValue = Long.valueOf(0);
		if (isNum(inp[2])) {
			newValue = regs.get(inp[1]) % Long.parseLong(inp[2]);
		}
		else {
			newValue = regs.get(inp[1]) % regs.get(inp[2]); 
		}
		regs.put(inp[1], newValue);
	}

	public static boolean isNum(String value) {
		try {
			Long.parseLong(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}