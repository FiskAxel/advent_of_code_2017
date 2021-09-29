import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class day25 {
	int w0; int m0; String c0;
	int w1; int m1; String c1;
	day25(int w0, int m0, String c0,int w1, int m1, String c1) {
		this.w0 = w0; this.m0 = m0; this.c0 = c0;
		this.w1 = w1; this.m1 = m1; this.c1 = c1;
	}
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input25.txt");
        Scanner reader = new Scanner(in);
		ArrayList<String> input = new ArrayList<String>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            input.add(line);
        }
        reader.close();

		ArrayList<Integer> tape = new ArrayList<Integer>();
		int cursor = 0;
		String state = "";
		String[] parse = input.get(0).split(" ");
		state = parse[3].substring(0, 1);
		int steps = 0;
		parse = input.get(1).split(" ");
		steps = Integer.parseInt(parse[5]);

		Hashtable<String, day25> stateInstructions = new Hashtable<String, day25>();
		parseInstructions(input, stateInstructions);

		for (int i = 0; i < steps; i++) {
			day25 current = stateInstructions.get(state);
			if (tape.contains(cursor)) {
				Write(current.w1, cursor, tape);
				cursor += current.m1;
				state = current.c1;
			}
			else {
				Write(current.w0, cursor, tape);
				cursor += current.m0;
				state = current.c0;
			}
		}

        int result = tape.size();
        System.out.println("Part 1: " + result);
    }
	public static void parseInstructions(ArrayList<String> input, Hashtable<String, day25> sI) {
		for (int i = 3; i < input.size(); i += 10) {
			String[] parse = input.get(i).split(" ");
			String sName = parse[2].substring(0, 1);


			String[] parseW = input.get(i + 2).split(" ");
			int w0 = Integer.parseInt(parseW[8].substring(0, 1));
			
			String[] parseM = input.get(i + 3).split(" ");
			int m0 = 1;
			if (parseM[10].equals("left.")) {
				m0 = - 1;
			}
			
			String[] parseC = input.get(i + 4).split(" ");
			String c0 = parseC[8].substring(0, 1);
			
		
			parseW = input.get(i + 6).split(" ");
			int w1 = Integer.parseInt(parseW[8].substring(0, 1));
			
			parseM = input.get(i + 7).split(" ");
			int m1 = 1;
			if (parseM[10].equals("left.")) {
				m1 = - 1;
			}
			
			parseC = input.get(i + 8).split(" ");
			String c1 = parseC[8].substring(0, 1);


			day25 state = new day25(w0, m0, c0, w1, m1, c1);
			sI.put(sName, state);
		}
	}
	public static void Write(int input, int cursor, ArrayList<Integer> tape){
		if (input == 1 && !tape.contains(cursor)) {
			tape.add(cursor);
		}
		else if (input == 0 && tape.contains(cursor)) {
			tape.remove(tape.indexOf(cursor));
		}
	}
}