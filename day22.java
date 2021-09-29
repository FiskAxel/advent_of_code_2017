import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day22 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input22.txt");
        Scanner reader = new Scanner(in);
        ArrayList<String> input = new ArrayList<String>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            input.add(line);
        }
        reader.close();

        ArrayList<int[]> infected = new ArrayList<int[]>();
        int center = input.size() / 2;
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.size(); x++) {
                if (input.get(y).charAt(x) == '#') {
                    int[] node = new int[2];
                    node[0] = center - y;
                    node[1] = x - center;
                    infected.add(node);
                }
            }
        }

        int direction = 0; // up 0 right 1 down 2 left 3
        int[] position = new int[2];
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            //
            if (isInList(infected, position)) {
                direction = (direction + 1) % 4;
            }
            else {
                direction = (direction - 1) % 4;
                if (direction < 0) {
                    direction += 4;
                }
                int[] newInf = position.clone();
                infected.add(newInf);
                result++;
            }

            moveVirus(position, direction);
        }
        System.out.println("Part 1: " + result);

        //
        /// PART 2 (Takes an hour :( )
        // 

        infected = new ArrayList<int[]>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.size(); x++) {
                if (input.get(y).charAt(x) == '#') {
                    int[] node = new int[2];
                    node[0] = center - y;
                    node[1] = x - center;
                    infected.add(node);
                }
            }
        }
        direction = 0;
        position = new int[2];
        result = 0;
        ArrayList<int[]> w = new ArrayList<int[]>();
        ArrayList<int[]> f = new ArrayList<int[]>();

        for (int i = 0; i < 10000000; i++) {
            // Weakened
            if (isInList(w, position)) {
                int[] n = position.clone();
                infected.add(n);
                result++;
            }
            // Infected
            else if (isInList(infected, position)) {
                int[] n = position.clone();
                f.add(n);
                direction = (direction + 1) % 4;
            }
            // Flagged
            else if (isInList(f, position)) {
                direction = (direction + 2) % 4;
            }
            // Clean
            else {
                direction = (direction - 1) % 4;
                if (direction < 0) {
                    direction += 4;
                }
                int[] n = position.clone();
                w.add(n);
            }
            moveVirus(position, direction);
        }
        System.out.println("Part 2: " + result);
    }
    //removes from list too
    public static boolean isInList(ArrayList<int[]> inf, int[] pos) {
        for (int i = 0; i < inf.size(); i++) {
            if(inf.get(i)[0] == pos[0] && inf.get(i)[1] == pos[1]) {
                inf.remove(i);
                return true;
            }
        }
        return false;
    }
    public static void moveVirus(int[] pos, int dir) {
        if (dir == 0) {
            pos[0] += 1;
        }
        else if (dir == 2) {
            pos[0] -= 1;
        }
        else if (dir == 1) {
            pos[1] += 1;
        }
        else if (dir == 3) {
            pos[1] -= 1;
        }
    }
}