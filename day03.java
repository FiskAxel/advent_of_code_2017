import java.util.ArrayList;

public class day03 {
	int x;
	int y;
	int value;
	day03(int x, int y) {
		this.x = x;
		this.y = y;
	}
    public static void main(String[] args) {
        int input = 361527;
        int num = 1;
        while (num * num < input) {
            num += 2;
        }

        int sidesteps = 0;
        int U = num * num - (num - 1);
        int R = num * num - (num - 1) * 2;
        int D = num * num - (num - 1) * 3;
        int L = num * num - (num - 1) * 4;
        if (U <= input){
            sidesteps = Math.abs(U + num / 2 - input);
        }
        else if (R <= input){
            sidesteps = Math.abs(R + num / 2 - input);
        }
        else if (D <= input){
            sidesteps = Math.abs(D + num / 2 - input);
        }
        else if (L < input){
            sidesteps = Math.abs(L + num / 2 - input);
        }

        int result = num / 2 + sidesteps;
        System.out.println("Part 1: " + result);

        // PART 2

		day03 zero = new day03(0, 0);
		day03 one = new day03(1, 0);
		zero.value = 1;
		one.value = 1;
		ArrayList<day03> squares = new ArrayList<day03>();
		squares.add(zero);
		squares.add(one);
		char direction = 'r';
		while(squares.get(squares.size() - 1).value < input) {
			day03 latest = squares.get(squares.size() - 1);
			
			boolean r = false;
			boolean u = false;
			boolean l = false;
			boolean d = false;
			for (int i = 0; i < squares.size() - 1; i++) {
				day03 c = squares.get(i);
				if (latest.x + 1 == c.x && latest.y == c.y) {
					r = true;
				}
				else if (latest.x == c.x && latest.y + 1 == c.y) {
					u = true;
				}
				else if (latest.x - 1 == c.x && latest.y == c.y) {
					l = true;
				}
				else if (latest.x == c.x && latest.y - 1 == c.y) {
					d = true;
				}
			}
			if (direction == 'r' && !u) {
				direction = 'u';
			}
			else if (direction == 'u' && !l) {
				direction = 'l';
			}
			else if (direction == 'l' && !d) {
				direction = 'd';
			}
			else if (direction == 'd' && !r) {
				direction = 'r';
			}

			int x = latest.x;
			int y = latest.y;
			if (direction == 'r') {
				x++;
			}
			else if (direction == 'u') {
				y++;
			}
			else if (direction == 'l') {
				x--;
			}
			else if (direction == 'd') {
				y--;
			}
			day03 next = new day03(x, y);
			for (int i = 0; i < squares.size(); i++) {
				day03 c = squares.get(i);
				if (c.x == x - 1 && c.y == y + 1 ||
					c.x == x && c.y == y + 1 ||
					c.x == x + 1 && c.y == y + 1 ||
					c.x == x - 1 && c.y == y ||
					c.x == x + 1 && c.y == y ||
					c.x == x - 1 && c.y == y - 1 || 
					c.x == x && c.y == y - 1 ||
					c.x == x + 1 && c.y == y - 1) {
						next.value += c.value;
					}
			}
			squares.add(next);

			if (next.value > input) {
				break;
			}
		}

        result = squares.get(squares.size() - 1).value;
        System.out.println("Part 2: " + result);
    }
}