import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day02 {
    public static void main(String[] args) throws FileNotFoundException {
        int result1 = 0;
        int result2 = 0;
        File in = new File("input02.txt");
        Scanner reader = new Scanner(in);        
        while (reader.hasNextLine()) {
            String input = reader.nextLine();
            String[] numbers = input.split("\t");
            int min = Integer.MAX_VALUE;
            int max = 0;
            for (int i = 0; i < numbers.length; i++) {
                int num = Integer.parseInt(numbers[i]);
                if (num > max) {
                    max = num;
                }
                if (num < min) {
                    min = num;
                }
            }
            result1 += max - min;

            for (int i = 0; i < numbers.length; i++) {
                int num = Integer.parseInt(numbers[i]);
                for (int j = i + 1; j < numbers.length; j++) {
                    int num2 = Integer.parseInt(numbers[j]);
                    if (num % num2 == 0) {
                        result2 += num / num2;
                    }
                    if (num2 % num == 0) {
                        result2 += num2 / num;
                    }
                }
            }
        }
        reader.close();
        System.out.println("Part 1: " + result1);
        System.out.println("Part 2: " + result2);
    }
}
