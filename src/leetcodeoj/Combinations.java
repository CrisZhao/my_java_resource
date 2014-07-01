package leetcodeoj;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Given two integers n and k, return all possible combinations of k numbers out
 * of 1 ... n.
 * 
 * For example, If n = 4 and k = 2, a solution is:
 * 
 * [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 * 
 */
public class Combinations {
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		// if (n < k || k < 1) {
		// return result;
		// }

		// return combine1(1, n, k);
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		combine2(result, tmp, n, k, 1);
		return result;
	}

	private void combine2(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> tmp, int n, int k, int pos) {
		if (tmp.size() == k) {
			result.add(new ArrayList<Integer>(tmp));
			return;
		}
		for (int i = pos; i <= n; i++) {
			tmp.add(i);
			combine2(result, tmp, n, k, i + 1);
			tmp.remove(tmp.size() - 1);
		}
	}

	public ArrayList<ArrayList<Integer>> combine1(int start, int end, int k) {
		ArrayList<ArrayList<Integer>> combine = new ArrayList<ArrayList<Integer>>();
		if (k == 1) {
			for (int i = start; i <= end; i++) {
				ArrayList<Integer> line = new ArrayList<Integer>();
				line.add(i);
				combine.add(line);
			}
			return combine;
		}
		if (end - start + 1 < k) {
			return combine;
		}
		ArrayList<ArrayList<Integer>> sub = combine1(start + 1, end, k - 1);
		for (ArrayList<Integer> line : sub) {
			line.add(0, start);
			combine.add(line);
		}
		combine.addAll(combine1(start + 1, end, k));
		return combine;
	}

	@Test
	public void test() {
		System.out.println(combine(4, 4));
	}

}
