import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class day13 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input13.txt");
        Scanner reader = new Scanner(in);
        ArrayList<int[]> input = new ArrayList<int[]>();
		while (reader.hasNextLine()) {
			String[] line = reader.nextLine().split(": ");
			int[] parse = { Integer.parseInt(line[0]), Integer.parseInt(line[1]) };
			input.add(parse);
		}
        reader.close();

		int result = severity(0, input);
        System.out.println("Part 1: " + result);
      
		int i = 0;
		while(true) {
			i++;
			if (i % (input.get(0)[1] * 2 - 2) != 0 && 
			severity(i, input) == 0) {
				result = i;
				break;
			}
		}
        System.out.println("Part 2: " + result);
    }
	
	static int severity(int start, ArrayList<int[]> input) {
		int result = 0;
		for (int i = 1; i < input.size(); i++) {
			int[] next = input.get(i);
			int range = next[1];
			if ((range + range - 2) % next[0] + start == 0 && range < next[0] + start || 
				(next[0] + start) % (range * 2 - 2) == 0) {
				result += range * next[0];
			}
		}
		return result;
	}
}