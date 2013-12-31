package leetcodeoj;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Given a collection of numbers, return all possible permutations.
 * 
 * For example, [1,2,3] have the following permutations: [1,2,3], [1,3,2],
 * [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * 
 */
public class Permutation {

	public ArrayList<ArrayList<Integer>> permute(int[] num) {

		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permute(num, result, 0);
		return result;
	}

	private void permute(int[] num, ArrayList<ArrayList<Integer>> result, int i) {
		if(i==num.length) {
			result.add(convert(num));
		}
		for (int j = i; j < num.length; j++) {
			swap(num, i, j);
			permute(num, result, i+1);
			swap(num, i, j);
		}
		
	}
	
	private ArrayList<Integer> convert(int[] num) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i : num) {
			result.add(i);
		}
		return result;
	}

	private void swap(int[] num, int i, int j) {
		int temp = num[i];
		num[i]=num[j];
		num[j]=temp;
	}
	
	@Test
	public void test() {
		int a[] = {1,2,3};
		System.out.println(permute(a));
	}
}
