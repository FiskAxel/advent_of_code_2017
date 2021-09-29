import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day08 {
	String name;
	int value;

	day08(String name) {
		this.name = name;
		this.value = 0;
	}
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input08.txt");
        Scanner reader = new Scanner(in);
		ArrayList<String> input = new ArrayList<String>(); 
		while (reader.hasNextLine()) {
			input.add(reader.nextLine());
		}
        reader.close();

		ArrayList<day08> regs = new ArrayList<day08>();
		for (int i = 0; i < input.size(); i++) {
			Boolean next = false;
			String name = input.get(i).substring(0, input.get(i).indexOf(' '));
			for (int j = 0; j < regs.size(); j++) {
				if (name.equals(regs.get(j).name)) {
					next = true;
					break;
				}
			}
			if (next) {continue;}
			day08 newreg = new day08(name);
			regs.add(newreg);
		}

        int result2 = 0;
		for (int i = 0; i < input.size(); i++) {
			String[] split = input.get(i).split(" ");
			String reg = split[0];
			String incdec = split[1];
			int value = Integer.parseInt(split[2]);
			String logReg = split[4];
			String log = split[5];
			int logVal = Integer.parseInt(split[6]);

			if (logicTrue(logReg, log, logVal, regs)) {
				if (incdec.equals("inc")) {
					regs.get(iOf(reg, regs)).value += value;
				}
				else {
					regs.get(iOf(reg, regs)).value -= value;
				}
				if (result2 < regs.get(iOf(reg, regs)).value) {
					result2 = regs.get(iOf(reg, regs)).value;
				}
			}
		}
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < regs.size(); i++) {
			if( regs.get(i).value > result) {
				result = regs.get(i).value;
			}
		}
        System.out.println("Part 1: " + result);
        System.out.println("Part 2: " + result2);
    }
	public static int iOf(String name, ArrayList<day08> regs) {
		for (int i = 0; i < regs.size(); i++) {
			if (regs.get(i).name.equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public static boolean logicTrue(String reg, String op, int value, ArrayList<day08> regs) {
		switch (op) {
				case "<":
					if (regs.get(iOf(reg, regs)).value < value) {
						return true;
					}
					return false;
				case "<=":
					if (regs.get(iOf(reg, regs)).value <= value) {
						return true;
					}
					return false;
				case "==":
					if (regs.get(iOf(reg, regs)).value == value) {
						return true;
					}
					return false;
				case ">=":
					if (regs.get(iOf(reg, regs)).value >= value) {
						return true;
					}
					return false;
				case ">":
					if (regs.get(iOf(reg, regs)).value > value) {
						return true;
					}
					return false;
				case "!=":
					if (regs.get(iOf(reg, regs)).value != value) {
						return true;
					}
					return false;
				default:
					System.out.println("error \"logicTrue\"");
			}
		return false;
	}
}