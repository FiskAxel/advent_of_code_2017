import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class day07 {
	String name;
	int weight;
	int total;
	ArrayList<day07> branches;

	day07(String name) {
		this.name = name;
		this.weight = 0;
		this.total = 0;
		this.branches = null;
	}
    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("input07.txt");
        Scanner reader = new Scanner(in);
        ArrayList<String> mabayRoots = new ArrayList<>();
		ArrayList<String> notRoots = new ArrayList<>();
		ArrayList<String> discs = new ArrayList<>();
		while (reader.hasNextLine()) {
			String input = reader.nextLine();
			discs.add(input);
			if (input.contains("->")) {
				mabayRoots.add(input.substring(0, input.indexOf(' ')));
				String parse = input.substring(input.indexOf("-> ") + 3);
				String[] parse2 = parse.split(", ");
				Collections.addAll(notRoots, parse2);
			}
		}
        reader.close();
		
		String result = "";
		for (int i = 0; i < mabayRoots.size(); i++) {
			boolean notInNot = true;
			for (int j = 0; j < notRoots.size(); j++) {
				if (mabayRoots.get(i).equals(notRoots.get(j))) {
					notInNot = false;
				}
			}
			if (notInNot) {
				result = mabayRoots.get(i);
			}
		}
        System.out.println("Part 1: " + result);
        System.out.println("Part 2: ");

        // PART 2
		day07 root = new day07(result);
		root = BuildTree(root, discs);
		FindInbalance(root);
        System.out.println("(Last line is correct since its the difference furthers from the root!)");
    }
	public static day07 BuildTree(day07 node, ArrayList<String> discs) {
		String nodeInfo = discs.get(GetI(node.name, discs));
		ArrayList<day07> branches = new ArrayList<>();
		if (nodeInfo.contains("->")) {
			String parse = nodeInfo.substring(nodeInfo.indexOf("-> ") + 3);
			String[] branchNames = parse.split(", ");
			for (int i = 0; i < branchNames.length; i++) {
				day07 branch = new day07(branchNames[i]);
				branches.add(branch);
			}			
			for (int i = 0; i < branches.size(); i++) {
				BuildTree(branches.get(i), discs);
			}
		}

		node.weight = Integer.parseInt(nodeInfo.substring(nodeInfo.indexOf('(') + 1, nodeInfo.indexOf(')')));
		node.total = node.weight;
		for (int i = 0; i < branches.size(); i++) {
			node.total += branches.get(i).total;
		}
		node.branches = branches;

		return node;
	}

	public static int GetI(String name, ArrayList<String> lines) {
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).substring(0, lines.get(i).indexOf(' ')).equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public static void FindInbalance(day07 disc) {
		for (int i = 1; i < disc.branches.size(); i++) {
			if (disc.branches.get(0).total != (disc.branches.get(i).total)) {
				int dif = disc.branches.get(0).total - disc.branches.get(i).total;
				System.out.print("Mabey " + (disc.branches.get(0).weight + dif));
				System.out.println(" but probably " + (disc.branches.get(i).weight + dif));
			}
		}
		for (int i = 0; i < disc.branches.size(); i++) {
			FindInbalance(disc.branches.get(i));
		}
	}
}