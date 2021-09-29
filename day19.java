import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class day19 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input19.txt");
        Scanner reader = new Scanner(in);
		ArrayList<String> path = new ArrayList<String>();
        while(reader.hasNextLine()) {
			path.add(reader.nextLine());
		}
        reader.close();

		int x = path.get(0).indexOf("|");
		int y = 0;
		int lr = 0;
		int ud = 1;
		String result = "";
		int result2 = 0;
		while(true) {
			result2++;
			char prev = path.get(y).charAt(x);
			char current = path.get(y + ud).charAt(x + lr);
			x += lr;
			y += ud;
			if(current == ' ') {
				break;
			}
			else if (current == '+') {
				char L = path.get(y).charAt(x - 1);
				char R = path.get(y).charAt(x + 1);
				char U = path.get(y - 1).charAt(x);
				char D = path.get(y + 1).charAt(x);
				if (L != ' ' && L != prev) {
					lr = -1;
					ud = 0;
				}
				else if (R != ' ' && R != prev) {
					lr = 1;
					ud = 0;
				}
				else if (U != ' ' && U != prev) {
					lr = 0;
					ud = -1;
				}
				else if (D != ' ' && D != prev) {
					lr = 0;
					ud = 1;
				}
			}
			else if (current == '|' || current == '-') {

			}
			else {
				result += current;
			}
		}       
        System.out.println("Part 1: " + result);
        System.out.println("Part 2: " + result2);
    }
}