import java.util.ArrayList;

public class day14 {
	int x;
	int y;
	day14(int x, int y) {
		this.x = x;
		this.y = y;
	}
    public static void main(String[] args) {
		String input = "ugkiagan";
		int result = 0;
		String[] field = new String[128];
		for (int i = 0; i < 128; i++) {
			String hash = knotHash(input + String.valueOf(i));
			String bin = "";
			for (int j = 0; j < hash.length(); j++) {
				bin += hexToBin(hash.charAt(j));
			}
			field[i] = bin;
			for (int j = 0; j < bin.length(); j++) {
				if (bin.charAt(j) == '1') {
					result++;
				}
			}
		}
        System.out.println("Part 1: " + result);

		ArrayList<ArrayList<day14>> groups = new ArrayList<ArrayList<day14>>();
		for (int y = 0; y < 128; y++) {
			for (int x = 0; x < 128; x++) {	
				if (field[y].charAt(x) == '1' && !inGroups(groups, x, y)) {
					ArrayList<day14> group = new ArrayList<day14>();
					day14 square = new day14(x, y);
					group.add(square);
					addNeighbours(field, group);
					groups.add(group);
				}
			}
		}

        System.out.println("Part 2: " + groups.size());
    }

	
	public static String hexToBin(char c) {
		String output = "";
		if (c == '0') {
			output = "0000";
		}
		else if (c == '1') {
			output = "0001";
		}
		else if (c == '2') {
			output = "0010";
		}
		else if (c == '3') {
			output = "0011";
		}
		else if (c == '4') {
			output = "0100";
		}
		else if (c == '5') {
			output = "0101";
		}
		else if (c == '6') {
			output = "0110";
		}
		else if (c == '7') {
			output = "0111";
		}
		else if (c == '8') {
			output = "1000";
		}
		else if (c == '9') {
			output = "1001";
		}
		else if (c == 'a') {
			output = "1010";
		}
		else if (c == 'b') {
			output = "1011";
		}
		else if (c == 'c') {
			output = "1100";
		}
		else if (c == 'd') {
			output = "1101";
		}
		else if (c == 'e') {
			output = "1110";
		}
		else if ( c == 'f') {
			output = "1111";
		}
		return output;
	}
	//Part 2
	public static boolean inGroups(ArrayList<ArrayList<day14>> groups, int x, int y) {
		for (int i = 0; i < groups.size(); i++) {
			ArrayList<day14> group = groups.get(i);
			for (int j = 0; j < group.size(); j++) {
				day14 s = group.get(j);
				if (x == s.x && y == s.y) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean inGroup(ArrayList<day14> group, int x, int y) {	
		for (int j = 0; j < group.size(); j++) {
			day14 s = group.get(j);
			if (x == s.x && y == s.y) {
				return true;
			}
		}		
		return false;
	}
	public static void addNeighbours(String[] field, ArrayList<day14> group) {
		for (int i = 0; i < group.size(); i++) {
			int x = group.get(i).x;
			int y = group.get(i).y;
			if (y > 0 && field[y - 1].charAt(x) == '1' && !inGroup(group, x, y - 1)) {
				day14 neighbour = new day14(x, y - 1);
				group.add(neighbour);
			}
			if (x > 0 && field[y].charAt(x - 1) == '1' && !inGroup(group, x - 1, y)) {
				day14 neighbour = new day14(x - 1,y);
				group.add(neighbour);
			}
			if (y < 127 && field[y + 1].charAt(x) == '1' && !inGroup(group, x, y + 1)) {
				day14 neighbour = new day14(x, y + 1);
				group.add(neighbour);
			}
			if (x < 127 && field[y].charAt(x + 1) == '1' && !inGroup(group, x + 1, y)) {
				day14 neighbour = new day14(x + 1, y);
				group.add(neighbour);
			}
		}
	}
	//From day10
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
}