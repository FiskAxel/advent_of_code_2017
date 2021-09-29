import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day01 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("input01.txt");
        Scanner reader = new Scanner(myObj);
        String input = reader.nextLine();
        reader.close();

        String matches = "";
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                matches += input.charAt(i);
            }
        }

        if (input.charAt(input.length() - 1 ) == input.charAt(0)) {
            matches += input.charAt(0);
        }

        int result = 0;
        for (int i = 0; i < matches.length(); i++) {
            result += Integer.parseInt(matches.substring(i, i + 1));
        }

        System.out.println("Part 1: " + result);


        // PART 2

        matches = "";
        int len = input.length();
        for (int i = 0; i < len / 2; i++) {
            if (input.charAt(i) == input.charAt((i + (len / 2)) % len)) {
                matches += input.charAt(i);
                matches += input.charAt(i);
            }
        }

        result = 0;
        for (int i = 0; i < matches.length(); i++) {
            result += Integer.parseInt(matches.substring(i, i + 1));
        }

        System.out.println("Part 2: " + result);
    }
}