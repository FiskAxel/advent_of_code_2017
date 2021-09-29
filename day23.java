import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day23 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input23.txt");
        Scanner reader = new Scanner(in);
        ArrayList<String> input = new ArrayList<String>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            input.add(line);
        }
        reader.close();

        HashMap<String, Integer> regs = new HashMap<String, Integer>();
        for (int i = 0; i < 8; i++) {
            char a = (char)(97 + i);
            String b = "" + a;
            regs.put(b, 0);
        }

        int result = 0;
        for (int i = 0; i < input.size(); i++) {
			String[] inp = input.get(i).split(" ");
			if (inp[0].equals("set")) {
				set(inp, regs);	
			}
			else if (inp[0].equals("sub")) {
				sub(inp, regs);
			}
			else if (inp[0].equals("mul")) {
				mul(inp, regs);
                result++;
			}
			else if (inp[0].equals("jnz")) {
				i += jnz(inp, regs);
			}
        }
 
        System.out.println("Part 1: " + result);

        //
        /// PART 2
        //

		// Calculates the number of none prime numbers
		// starting from 108100 and adding 17 a thousand times.  
		input23translationWhenAis1();
    }
    public static void set(String[] inp, HashMap<String, Integer> regs) {
        int newValue = 0;
        if (isNum(inp[2])) {
			newValue = Integer.parseInt(inp[2]);
		}
		else {
			newValue = regs.get(inp[2]);
		}
		regs.put(inp[1], newValue);
    }
    public static void sub(String[] inp, HashMap<String, Integer> regs) {
        int newValue = 0;
        if (isNum(inp[2])) {
			newValue = regs.get(inp[1]) - Integer.parseInt(inp[2]);
		}
		else {
			newValue = regs.get(inp[1]) - regs.get(inp[2]); 
		}
		regs.put(inp[1], newValue);
    }
    public static void mul(String[] inp, HashMap<String, Integer> regs) {
        int newValue = 0;
        if (isNum(inp[2])) {
			newValue = regs.get(inp[1]) * Integer.parseInt(inp[2]);
		}
		else {
			newValue = regs.get(inp[1]) * regs.get(inp[2]); 
		}
		regs.put(inp[1], newValue);
    }
    public static int jnz(String[] inp, HashMap<String, Integer> regs) {
        int val = 0;
        if (isNum(inp[1])) {
            val = Integer.parseInt(inp[1]);
        }
        else {
            val = regs.get(inp[1]);
        }

        if (val != 0) {
            if (isNum(inp[2])) {
                return Integer.parseInt(inp[2]) - 1;
            }
            else {
                return regs.get(inp[2]) - 1;
            }
		}
        return 0;
    }

    public static boolean isNum(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void input23translationWhenAis1() {
		int b = 108100;
		int c = b + 17000;
		int d = 0;
		int e = 0;
		int f = 0;
		int h = 0;
		while (true) {
			f = 1;
			d = 2;
			// modified d != b. f == 1 added
			while(d <= Math.sqrt((double)b) && f == 1) {
				e = 2;
				// modified e != b
				while (e <= b / 2) {
					if (d * e == b) {
						f = 0;
						break;
					}
					e++;
				}
				d++;
			}
			if (f == 0) {
				h++;
			}
			if (b == c) {
				break;
			}
			b += 17;
		}
		System.out.println("Part 2: " + h);
	}
}