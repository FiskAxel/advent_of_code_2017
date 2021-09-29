import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class day20 {
	Long x;
	Long y;
	Long z;
	day20(Long x, Long y, Long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public static Long origoDist(day20 p) {	
		return Math.abs(p.x) + Math.abs(p.y) + Math.abs(p.z);
	}

    public static void main(String[] args) throws FileNotFoundException {
       	ArrayList<day20> p = new ArrayList<day20>();
		ArrayList<day20> v = new ArrayList<day20>();
		ArrayList<day20> a = new ArrayList<day20>();
		initialState(p, v, a);
		for (int h = 0; h < 1000; h++) {
			for (int i = 0; i < p.size(); i++) {
				day20 pe = p.get(i);
				day20 ve = v.get(i);
				day20 ae = a.get(i);
				ve.x += ae.x;
				ve.y += ae.y;
				ve.z += ae.z;
				pe.x += ve.x;
				pe.y += ve.y;
				pe.z += ve.z;
			}
		}
		int index = 0;
		Long min = Long.MAX_VALUE;
		for (int i = 1; i < p.size(); i++) {
			Long sum = (origoDist(p.get(i)));
			if (sum <= min) {
				index = i;
				min = sum;
			}
		}
        System.out.println("Part 1: " + index);

		//
		/// PART 2 Cheaty version (simulates 1000 ticks. Works for my input but takes a minute)
		//

		p = new ArrayList<day20>();
		v = new ArrayList<day20>();
		a = new ArrayList<day20>();
		initialState(p, v, a);
		ArrayList<Integer> removed = new ArrayList<Integer>();
		for (int h = 0; h < 1000; h++) {
			ArrayList<Integer> remove = new ArrayList<Integer>();
			for (int i = 0; i < p.size(); i++) {
				if (removed.contains(i)) {
					continue;
				}
				day20 pi = p.get(i);
				for (int j = i + 1; j < p.size(); j++) {
					if (removed.contains(j)) {
						continue;
					}
					day20 pj = p.get(j);
					if (pi.x == pj.x && pi.y == pj.y && pi.z == pj.z) {
						if (!remove.contains(i)) {
							remove.add(i);
						}
						if (!remove.contains(j)) {
							remove.add(j);
						}
					}
				}
			}
 			for (int i = 0; i < remove.size(); i++) {
				removed.add(remove.get(i));
			}

			for (int i = 0; i < p.size(); i++) {
				day20 pe = p.get(i);
				day20 ve = v.get(i);
				day20 ae = a.get(i);
				ve.x += ae.x;
				ve.y += ae.y;
				ve.z += ae.z;
				pe.x += ve.x;
				pe.y += ve.y;
				pe.z += ve.z;
			}
		}
		System.out.println("Part 2: " + (1000 - removed.size()));
		





		//
		/// PART 2 Not workey version :(
		//

		// ArrayList<ArrayList<Integer>> collisions = new ArrayList<ArrayList<Integer>>();
		// for (int i = 0; i < p.size(); i++) {
		// 	day20 pi = p.get(i);
		// 	day20 vi = v.get(i);
		// 	day20 ai = a.get(i);
		// 	ArrayList<Integer> colTimes = new ArrayList<Integer>();
		// 	ArrayList<int[]> tp = new ArrayList<int[]>();
		// 	for (int j = i + 1; j < p.size(); j++) {
		// 		day20 pj = p.get(j);
		// 		day20 vj = v.get(j);
		// 		day20 aj = a.get(j);
		// 		int t = pq((ai.x - aj.x)/2, (vi.x + ai.x/2) - (vj.x + aj.x/2), (pi.x - pj.x));
		// 		if (t >= 0 && yzCol(t, ai, vi, pi, aj, vj, pj)) {
		// 			if (!colTimes.contains(t)) {
		// 				colTimes.add(t);
		// 			}
		// 			int[] newTp = new int[2];
		// 			newTp[0] = t;
		// 			newTp[1] = j;
		// 			tp.add(newTp);
		// 		}
		// 	}
		// 	Collections.sort(colTimes);
		// 	for (int j = 0; j < colTimes.size(); j++) {
		// 		ArrayList<Integer> psAtT = new ArrayList<Integer>();
		// 		psAtT.add(colTimes.get(j));
		// 		psAtT.add(i);
		// 		for (int k = 0; k < tp.size(); k++) {
		// 			if (colTimes.get(j) == tp.get(k)[0]) {
		// 				psAtT.add(tp.get(k)[1]);
		// 			}
		// 		}
		// 		collisions.add(psAtT);
		// 	}
		// }



		// sortByT(collisions);
		// ArrayList<Integer> removed = new ArrayList<Integer>();
		// for (int i = 0; i < collisions.size(); i++) {
		// 	int particlesLeft = 0;
		// 	for (int j = 1; j < collisions.get(i).size(); j++) {
		// 		if(!removed.contains(collisions.get(i).get(j))) {
		// 			particlesLeft++;
		// 		}
		// 	}
		// 	if (particlesLeft > 1) {
		// 		for (int j = 1; j < collisions.get(i).size(); j++) {
		// 			if(!removed.contains(collisions.get(i).get(j))) {
		// 				removed.add(collisions.get(i).get(j));
		// 			}
		// 		}
		// 	}
		// }
		//
		//System.out.println("Part 2: " + (p.size() - removed.size()));
    }
	public static void initialState(ArrayList<day20> p, ArrayList<day20> v, ArrayList<day20> a) throws FileNotFoundException {
		File in = new File("input20.txt");
        Scanner reader = new Scanner(in);
		while(reader.hasNextLine()) {
			String p1 = reader.nextLine();
			String[] p2 = p1.split(">, ");
			String[] p3 = p2[0].substring(3).split(",");
			day20 pee = new day20(getNum(p3[0]), getNum(p3[1]), getNum(p3[2]));
			p.add(pee);
			p3 = p2[1].substring(3).split(",");
			day20 vee = new day20(getNum(p3[0]), getNum(p3[1]), getNum(p3[2]));
			v.add(vee);
			p3 = p2[2].substring(3).split(",");
			day20 aee = new day20(getNum(p3[0]), getNum(p3[1]), getNum(p3[2].substring(0, p3[2].length() - 1)));
			a.add(aee);
		}	
        reader.close();
	}
	public static long getNum(String a) {
		if (a.charAt(0) == '-') {
			return -Long.parseLong(a.substring(1));
		}
		return Long.parseLong(a);
	}

// 	public static int pq(double a, double b, double c) {
// 		if (a == 0) {
// 			double ans = -c/b;
// 			if (ans % 1 == 0) {
// 				return (int)ans;
// 			}
// 			return -1;
// 		}
// 		double p = b / a;
// 		double q = c / a;
// 		double pow = Math.pow((p / 2), 2);
// 		if (pow >= q) {
// 			double t1 = (-p/2) + Math.sqrt(pow - q);
// 			double t2 = (-p/2) - Math.sqrt(pow - q);
// 			if (0 <= t1 && t1 <= t2 && t1 % 1 == 0 ||
// 				0 <= t1 && t2 < 0 && t1 % 1 == 0) {
// 				return (int)t1;
// 			}
// 			else if (0 <= t2 && t2 % 1 == 0) {
// 				return (int)t2;
// 			}
// 		}
// 		return -1;
// 	}
// 	public static boolean yzCol(int t, day20 ai, day20 vi, day20 pi, day20 aj, day20 vj, day20 pj) {
// 		double a = (ai.y - aj.y)/2; 
// 		double b = (vi.y + ai.y/2) - (vj.y + aj.y/2);
// 		double c = (pi.y - pj.y);
// 		if (a * Math.pow(t, 2) + b * t + c != 0) {
// 			return false;
// 		}
// 		a = (ai.z - aj.z)/2;
// 		b = (vi.z + ai.z/2) - (vj.z + aj.z/2);
// 		c = (pi.z - pj.z);
// 		if (a * Math.pow(t, 2) + b * t + c != 0) {
// 			return false;
// 		}	
// 		return true;
// 	}
// 	public static void sortByT(ArrayList<ArrayList<Integer>> l) {
// 		for (int i = 0; i < l.size(); i++) {
// 			for (int j = i + 1; j < l.size(); j++) {
// 				if (l.get(i).get(0) > l.get(j).get(0)) {
// 					ArrayList<Integer> temp = l.get(i);
// 					l.set(i, l.get(j));
// 					l.set(j, temp);
// 				}
// 			}
// 		}
// 	}
// }