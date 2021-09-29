import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day24 {
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input24.txt");
        Scanner reader = new Scanner(in);
        ArrayList<String> input = new ArrayList<String>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            input.add(line);
        }
        reader.close();

        ArrayList<ArrayList<Integer>> bridges = new ArrayList<ArrayList<Integer>>();  
        for (int i = 0; i < input.size(); i++) {
            String[] s = input.get(i).split("/");
            if (s[0].equals("0")) {
                ArrayList<Integer> br = new ArrayList<Integer>();
                br.add(i);
                validBridges(input, bridges, br, s[1]);
            }
            else if (s[1].equals("0")) {
                ArrayList<Integer> br = new ArrayList<Integer>();
                br.add(i);
                validBridges(input, bridges, br, s[0]);
            }
        }
        
        int result = stronknessOfStronkestBridge(bridges, input);
        System.out.println("Part 1: " + result);



        int longestLength = 5;
        for (int i = 0; i < bridges.size(); i++) {
            if (bridges.get(i).size() > longestLength) {
                longestLength = bridges.get(i).size();
            }
        }
        ArrayList<ArrayList<Integer>> longsters = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < bridges.size(); i++) {
            if (bridges.get(i).size() == longestLength) {
                longsters.add(bridges.get(i));
            }
        }
        result = stronknessOfStronkestBridge(longsters, input);
        System.out.println("Part 2: " + result);
    }
    public static void validBridges(ArrayList<String> adps, ArrayList<ArrayList<Integer>> bridges, ArrayList<Integer> newBridge, String nextC) {
        for (int i = 0; i < adps.size(); i++) {
            if (!newBridge.contains(i)){
                String[] next = adps.get(i).split("/");
                if (next[0].equals(nextC)) {
                    ArrayList<Integer> copy = cloned(newBridge);
                    copy.add(i);
                    validBridges(adps, bridges, copy, next[1]);
                }
                else if (next[1].equals(nextC)) {
                    ArrayList<Integer> copy = cloned(newBridge);
                    copy.add(i);
                    validBridges(adps, bridges, copy, next[0]);
                }
            }
        }   
        bridges.add(newBridge);     
    }
    public static int stronknessOfStronkestBridge(ArrayList<ArrayList<Integer>> bridges, ArrayList<String> apds) {
        int max = 0;
        for (int i = 0; i < bridges.size(); i++) {
            int sum = 0;
            for (int j = 0; j < bridges.get(i).size(); j++) {
                String[] s = apds.get(bridges.get(i).get(j)).split("/");
                sum += Integer.parseInt(s[0]);
                sum += Integer.parseInt(s[1]);
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    public static ArrayList<Integer> cloned(ArrayList<Integer> b) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (int i = 0; i < b.size(); i++) {
            output.add(b.get(i));
        }
        return output;
    }
}