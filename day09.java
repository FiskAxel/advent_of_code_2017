import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day09 {
	public int i;
	public int gc; //garbage count
	public day09() {
		this.i = 0;
		this.gc = 0;
	}

    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input09.txt");
        Scanner reader = new Scanner(in);
    	String input = reader.nextLine();
        reader.close();
		day09 outie = new day09();
		ArrayList<Integer> scores = new ArrayList<>();
	    int result = groupScore(input, outie, scores);
        System.out.println("Part 1: " + result);
        System.out.println("Part 2: " + outie.gc);
    }

	public static int groupScore(String input, day09 i, ArrayList<Integer> scores) {
		boolean garbage = false;
		int score = 0;
		for (score = 0; i.i < input.length(); i.i++) {
			if (input.charAt(i.i) == '!') {
				i.i++;
				continue;
			}
			else if (input.charAt(i.i) == '<' && !garbage) {
				garbage = true;
			}
			else if (input.charAt(i.i) == '>') {
				garbage = false;
			}
			else if (garbage) {
				i.gc++;
				continue;
			}
			else if (input.charAt(i.i) == '{') {
				i.i++;
				score += groupScore(input, i, scores);
			}
			else if (input.charAt(i.i) == '}') {
				scores.add(score);
				return score + 1;
			}
		}
		for (int j = 0; j < scores.size(); j++) {
			score += scores.get(j);
		}
		return score;
	}
}