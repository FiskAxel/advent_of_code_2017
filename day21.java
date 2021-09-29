import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day21 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input21.txt");
        Scanner reader = new Scanner(in);
		ArrayList<String> rules = new ArrayList<String>();
		while(reader.hasNextLine()) {
			rules.add(reader.nextLine());
		} reader.close();

		ArrayList<String> box = new ArrayList<String>();
		box.add(".#.");
		box.add("..#");
		box.add("###");

		for (int i = 0; i < 5; i++) {
			ArrayList<String> newBox = new ArrayList<String>();
			int type = 3;
			if (box.size() % 2 == 0) {
				type = 2;
			}
			for (int j = 0; j < box.size() + box.size() / type; j++) {
				newBox.add("");
			}
			for (int y = 0; y < box.size(); y += type) {
				for (int x = 0; x < box.size(); x += type) {
					buildBox(newBox, x, y, box, rules, type);
				}
			}
			box = newBox;
		}

        int result = 0;
		for (int i = 0; i < box.size(); i++) {
			for (int j = 0; j < box.size(); j++) {
				if (box.get(i).charAt(j) == '#') {
					result++;
				}
			}
		}
        System.out.println("Part 1: " + result);

		//
		// PART 2
		//

		for (int i = 0; i < 18; i++) {
			ArrayList<String> newBox = new ArrayList<String>();
			int type = 3;
			if (box.size() % 2 == 0) {
				type = 2;
			}
			for (int j = 0; j < box.size() + box.size() / type; j++) {
				newBox.add("");
			}
			for (int y = 0; y < box.size(); y += type) {
				for (int x = 0; x < box.size(); x += type) {
					buildBox(newBox, x, y, box, rules, type);
				}
			}
			box = newBox;
		}

        result = 0;
		for (int i = 0; i < box.size(); i++) {
			for (int j = 0; j < box.size(); j++) {
				if (box.get(i).charAt(j) == '#') {
					result++;
				}
			}
		}
        System.out.println("Part 2: " + result);
    }

	public static void buildBox(ArrayList<String> newBox, int x, int y, ArrayList<String> box, ArrayList<String> rules, int size) {
		ArrayList<String> b = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			b.add(box.get(y + i).substring(x, x + size));
		}
		for (int i = 0; i < rules.size(); i++) {
			String[] rule = rules.get(i).split(" => ");
			if (strBox(b).equals(rule[0]) ||
				rotateB(b, 1).equals(rule[0]) ||
				rotateB(b, 2).equals(rule[0]) ||
				rotateB(b, 3).equals(rule[0]) ||
				strBox(flipB(b)).equals(rule[0]) ||
				rotateB(flipB(b), 1).equals(rule[0]) ||
				rotateB(flipB(b), 2).equals(rule[0]) ||
				rotateB(flipB(b), 3).equals(rule[0]))
			{
				String[] mbox = rule[1].split("/");
				for (int j = 0; j < mbox.length; j++) {
					int index = ((y / size) * (size + 1)) + j;
					String extend = newBox.get(index);
					extend += mbox[j];
					newBox.set(index, extend);
				}
				return;
			}
		}	
	}
	public static String strBox(ArrayList<String> box) {
		String output = "";
		for (int i = 0; i < box.size(); i++) {
			output += box.get(i);
			output += "/";
		}
		return output.substring(0 , output.length() - 1);
	}
	public static String rotateB(ArrayList<String> box, int steps) {
		String output = "";
		if (steps == 1) {
			for (int i = box.size() - 1; i >= 0 ; i--) {
				for (int j = 0; j < box.size(); j++) {
					output += box.get(j).charAt(i);
				}
				output += "/";
			}
		}
		else if (steps == 2) {
			for (int i = box.size() - 1; i >= 0 ; i--) {
				for (int j = box.size() - 1; j >= 0; j--) {
					output += box.get(i).charAt(j);
				}
				output += "/";
			}
		}
		else if (steps == 3) {
			for (int i = 0; i < box.size(); i++) {
				for (int j = box.size() - 1; j >= 0; j--) {
					output += box.get(j).charAt(i);
				}
				output += "/";
			}
		}

		return output.substring(0 , output.length() - 1);
	}
	public static ArrayList<String> flipB(ArrayList<String> box) {
		ArrayList<String> output = new ArrayList<String>();
		for (int i = box.size() - 1; i >= 0; i--) {
			output.add(box.get(i));
		}
		return output;
	}
}