import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day16 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input16.txt");
        Scanner reader = new Scanner(in);
        String[] input = reader.nextLine().split(",");
        reader.close();
				
		String ogLine = "abcdefghijklmnop";
        System.out.println("Part 1: " + part1(ogLine, input));
		String line = ogLine;
		int ogFrequenzy = 0;
		boolean firstOg = true;
		for (int i = 0; i < 1000; i++) {
			line = part1(line, input);
			if (line.equals(ogLine)) {
				if (firstOg) {
					ogFrequenzy = i;
					firstOg = false;
					continue;
				}
				ogFrequenzy = i - ogFrequenzy;
				break;
			}
		}
		int loops = 1000000000 % ogFrequenzy;
		for (int i = 0; i < loops; i++) {
			line = part1(line, input);
		}

        System.out.println("Part 2: " + line);
    }
	public static String part1(String line, String[] input) {
		for (int i = 0; i < input.length; i++) {
			String parse = input[i].substring(1);
			if (input[i].charAt(0) == 's') {
				line = spin(line, parse);
				continue;
			}
			String parse2[] = parse.split("/");
			if (input[i].charAt(0) == 'x') {	
				int i1 = Integer.parseInt(parse2[0]);
				int i2 = Integer.parseInt(parse2[1]);
				line = exchange(line, i1, i2);
			}
			else if (input[i].charAt(0) == 'p') {
				line = partner(line, parse2[0], parse2[1]);
			}
		}
		return line;
	}

	public static String spin(String line, String move) {
		int i1 = Integer.parseInt(move);
		String newLine = line.substring(line.length() - i1);
		newLine += line.substring(0, line.length() - i1);
		return newLine;
	}
	public static String exchange(String line, int i1, int i2) {
		String newLine = "";
		for (int i = 0; i < line.length(); i++) {
			if (i == i1) {
				newLine += line.charAt(i2);
			}
			else if (i == i2) {
				newLine += line.charAt(i1);
			}
			else {
				newLine += line.charAt(i);
			}
		}
		return newLine;
	}
	public static String partner(String line, String p1, String p2) {
		int i1 = line.indexOf(p1);
		int i2 = line.indexOf(p2);
		return exchange(line, i1, i2);
	}
}