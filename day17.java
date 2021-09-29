import java.util.ArrayList;

public class day17 {
    public static void main(String[] args) {
        int input = 382;      
		ArrayList<Integer> spin = new ArrayList<Integer>();
		spin.add(0);
		int current = 0;
		for (int i = 1; i <= 2017; i++) {
			current = (current + input + 1) % i;
			spin.add(current, i);
		}
		System.out.println("Part 1: " + spin.get(current + 1));
		
		int zeroIndex = 0;
		int valueAfterZero = 1;
		for (int i = 1; i <= 50000000; i++) {
			current = (current + input + 1) % (i);
			if (current == (zeroIndex + 1) % i) {
				valueAfterZero = i;
			}
			if (current <= zeroIndex || current == i - 1) {
				zeroIndex++;
			}
		}      
        System.out.println("Part 2: " + valueAfterZero);
    }
}