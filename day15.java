public class day15 {
    public static void main(String[] args) {      
		long A = 679; //A = 65;
		long B = 771; //B = 8921;
		int facA = 16807;
		int facB = 48271;	
		
		part1(A, B, facA, facB);
		part2(A, B, facA, facB);
    }

	static String binary16(long num) {
		String bin = Long.toBinaryString(num);
		String output = "";
		for (int i = bin.length() - 16; i < bin.length(); i++) {
			if (i < 0) {
				output += '0';
			}
			else {
				output += bin.charAt(i);
			}
		}
		return output;
	}
	static void part1(long A, long B, int facA, int facB) {
		int result = 0;
		for (int i = 0; i < 40000000; i++) {
			A *= facA;
			B *= facB;
			A %= Integer.MAX_VALUE;
			B %= Integer.MAX_VALUE;
			if (binary16(A).equals(binary16(B))) {
				result++;
			}
		}   
		System.out.println("Part 1: " + result);
	}
	static void part2(long A, long B, int facA, int facB) {
		int result = 0;
		for (int i = 0; i < 5000000; i++) {
			while(true) {
				A *= facA;
				A %= Integer.MAX_VALUE;
				if (A % 4 == 0) {
					break;
				}
			}
			
			while (true) {
				B *= facB;
				B %= Integer.MAX_VALUE;
				if (B % 8 == 0) {
					break;
				}
			}
			
			if (binary16(A).equals(binary16(B))) {
				result++;
			}
		}   
		System.out.println("Part 2: " + result);
	}
}