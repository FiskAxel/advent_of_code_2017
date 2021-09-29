import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day06 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input06.txt");
        Scanner reader = new Scanner(in);
        String[] input = reader.nextLine().split("\t");
        reader.close();
		int[] banks = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			banks[i] = Integer.parseInt(input[i]);
		}

		String[] formations = new String[20000];
		int result = 0;
		int result2 = 0;
		while(true) {
			boolean found = false;
			String form = "";
			for (int i = 0; i < banks.length; i++) {
				form += String.valueOf(banks[i]);
				form += ',';
			}
			for (int i = 0; i < result; i++ ) {
				if (formations[i].equals(form)) {
					found = true;
					result2 = result - i;
					break;
				}
			}
			if(found) {
				break;	
			}
			formations[result] = form;
			result++;
			int max = 0;
			for (int i = 1; i < banks.length; i++) {
				if (banks[i] > banks[max]) {
					max = i;
				}
			}
			int blocks = banks[max];
			banks[max] = 0;
			int idx = (max + 1) % banks.length;
			while(blocks > 0) {
				banks[idx]++;
				blocks--;
				idx = (idx + 1) % banks.length;
			}
		}
        System.out.println("Part 1: " + result);
        System.out.println("Part 2: " + result2);
    }
}