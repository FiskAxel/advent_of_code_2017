import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day04 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input04.txt");
        Scanner reader = new Scanner(in);
        int result = 0;
        int result2 = 0;
        while (reader.hasNextLine()) {
            String[] input = reader.nextLine().split(" ");
            boolean valid = true;
            boolean valid2 = true;
            for (int i = 0; i < input.length; i++) {
                for (int j = i + 1; j < input.length; j++) {
                    if (input[i].equals(input[j])) {
                        valid = false;
                    }
                    if (alphSort(input[i]).equals(alphSort(input[j]))) {
                        valid2 = false;
                    }
                }
            }
            if (valid) {
                result++;
            }
            if (valid2) {
                result2++;
            }
            
        }
        reader.close();
        System.out.println("Part 1: " + result);
        System.out.println("Part 2: " + result2);
    }

    public static String alphSort(String in) {
        String alph = "abcdefghijklmnopqrstuvxyz";
        String out = "";
        for (int i = 0; i < alph.length(); i++) {
            for (int j = 0; j < in.length(); j++) {
                if (in.charAt(j) == alph.charAt(i)) {
                    out += in.charAt(j);
                }
            }
        }
        return out;
    }
}

