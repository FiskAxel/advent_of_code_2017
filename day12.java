import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day12 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input12.txt");
        Scanner reader = new Scanner(in);
		ArrayList<String> input = new ArrayList<String>();
		while (reader.hasNextLine()) {
			String l = reader.nextLine();
			input.add(l.substring(l.indexOf(">") + 2));
		}
        reader.close();

		ArrayList<Integer> group = part1(input, 0);
        System.out.println("Part 1: " + group.size());

		int groupCount = 1;
		for (int i = 1; i < input.size(); i++) {
			for (int j = 0; j < input.size(); j++) {
				if (group.contains(j)) {
					input.set(j, "");
				}
			}
			if (input.get(i) != "") {
				group = part1(input, i);
				groupCount++;
			}
		}
        System.out.println("Part 2: " + groupCount);
    }

	public static ArrayList<Integer> part1(ArrayList<String> input, int index) {
		ArrayList<Integer> connected = new ArrayList<Integer>();
		String[] parse = input.get(index).split(", ");
		for (int i = 0; i < parse.length; i++) {
			connected.add(Integer.parseInt(parse[i]));
		}
		boolean added = true;
		while (added) {
			added = false;
			for (int i = 0; i < connected.size(); i++) {
				parse = input.get(connected.get(i)).split(", ");
				for (int j = 0; j < parse.length; j++) {
					int num = Integer.parseInt(parse[j]);
					if (!connected.contains(num)) {
						connected.add(num);
						added = true;
					}
				}
			}
		}
		return connected;
	}
}