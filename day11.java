import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day11 {
	int x;
	int y;
	day11() {
		this.x = 0;
		this.y = 0;
	}
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input11.txt");
        Scanner reader = new Scanner(in);
        String[] input = reader.nextLine().split(",");
        reader.close();

		day11 pos = new day11(); 
		int result2 = getPositionPlus(pos, input);
        int result = shortestWay(pos.x, pos.y);
        System.out.println("Part 1: " + result);
        System.out.println("Part 2: " + result2);
    }

	public static int getPositionPlus(day11 pos, String[] input) {
		int furthestAway = 0;
		for (int i = 0; i < input.length; i++) {
			switch (input[i]) {
				case "n":
					pos.y += 2; break;
				case "s":
					pos.y -= 2; break;
				case "ne":
					pos.y++;
					pos.x++; break;
				case "se":
					pos.y--;
					pos.x++; break;
				case "nw":
					pos.y++;
					pos.x--; break;
				case "sw":
					pos.y--;
					pos.x--; break;
			}
			int distance = shortestWay(pos.x, pos.y);
			if (distance > furthestAway) {
				furthestAway = distance;
			}
		}
		return furthestAway;
	}
	public static int shortestWay(int x, int y) {
		int steps = 0;
		while(x != 0 || y != 0) {
			steps++;
			if (x > 0 && y > 0) {
				x--; y--;
			}
			else if (x < 0 && y > 0) {
				x++; y--;
			}
			else if ((x < 0 && y < 0) || (x < 0 && y == 0)) {
				x++; y++;
			}
			else if ((x > 0 && y < 0) || (x > 0 && y == 0)) {
				x--; y++;
			}
			else if (x == 0 && y > 0) {
				y--; y--;
			}
			else if (x == 0 && y < 0) {
				y++; y++;
			}
		}
		return steps;
	}
}