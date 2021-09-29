import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day05 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input05.txt");
        Scanner reader = new Scanner(in);
		int[] inst = new int[1078];
		for (int i = 0; i < inst.length; i++) {
			String input = reader.nextLine();
			inst[i] = Integer.parseInt(input);
		}
        reader.close();

        int result = 0;
		for (int i = 0; i < inst.length;) {
			int plus = inst[i];
			inst[i]++;
			i += plus;
			result++;
		}
        System.out.println("Part 1: " + result);

        // PART 2
        reader = new Scanner(in);
		for (int i = 0; i < inst.length; i++) {
			String input = reader.nextLine();
			inst[i] = Integer.parseInt(input);
		}
        reader.close();

        result = 0;
		for (int i = 0; i < inst.length;) {
			int plus = inst[i];
			if (inst[i] < 3) {
				inst[i]++;
			}
			else {
				inst[i]--;
			}
			i += plus;
			result++;
		}
        System.out.println("Part 2: " + result);
    }
}