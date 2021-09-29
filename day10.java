import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class day10 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input10.txt");
        Scanner reader = new Scanner(in);
        String[] input = (reader.nextLine()).split(",");
		int[] lengths = new int[input.length];
		
		for (int i = 0; i < lengths.length; i++) {
			lengths[i] = Integer.parseInt(input[i]);
		}
        reader.close();
		int[] numList = new int[256];
		for (int i = 0; i <= 255; i++) {
			numList[i] = i;
		}
		int position = 0;
		int skipsize = 0;

		for (int i = 0; i < lengths.length; i++) {
			reverseSegment(numList, position, lengths[i]);
			position += lengths[i] + skipsize;
			skipsize++;
		}

        int result = numList[0] * numList[1];
        System.out.println("Part 1: " + result);

        reader = new Scanner(in);
        String input2 = reader.nextLine();
		reader.close();
        System.out.println("Part 2: " + knotHash(input2));
    }

	public static void reverseSegment(int[] numList, int position, int length) {
		int[] reversed = new int[length];
		int j = 0;
		for (int i = position + length - 1; i >= position; i--) {
			reversed[j] = numList[i % numList.length];
			j++;
		}
		j = 0;
		for (int i = position; i < position + length; i++) {
			numList[i % numList.length] = reversed[j];
			j++;
		}

	}
	
	public static String hexify(int i, int[]nums) {
		int xor = nums[i];
		for	(int j = 1; j < 16; j++) {
			xor ^= nums[i + j];
		}
		if (xor < 16) {
			return 0 + Integer.toHexString(xor);
		}
		return Integer.toHexString(xor);
	}

	public static String knotHash(String input) {
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		for (int i = 0; i < input.length(); i++) {
			int c = input.charAt(i);
			lengths.add(c);
		}
		lengths.add(17); lengths.add(31); 
		lengths.add(73); lengths.add(47); 
		lengths.add(23);
		
		int[] numList = new int[256];
		for (int i = 0; i <= 255; i++) {
			numList[i] = i;
		}
		int position = 0;
		int skipsize = 0;

		for (int h = 0; h < 64; h++) {
			for (int i = 0; i < lengths.size(); i++) {
				reverseSegment(numList, position, lengths.get(i));
				position += lengths.get(i) + skipsize;
				skipsize++;
			}
		}

		String result = "";
		for (int i = 0; i < 16; i++) {
			result += hexify(i * 16, numList);
		}
		return result;
	}
}