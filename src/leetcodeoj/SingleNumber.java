package leetcodeoj;

/**
 * Given an array of integers, every element appears twice except for one. Find
 * that single one. Note: Your algorithm should have a linear runtime
 * complexity. Could you implement it without using extra memory?
 * 
 */
public class SingleNumber {

	public int singleNumber(int[] A) {
		int n = A.length;
		while (--n != 0) {
			A[n - 1] ^= A[n];
		}
		return A[0];
	}

	public static void main(String[] args) {
		int[] input = { 1, 2, 4, 5, 6, 3, 4, 3, 2, 6, 5 };
		System.out.println(new SingleNumber().singleNumber(input));
	}

	// @Test
	// public void test() {
	// int[] input = { 1, 2, 4, 5, 6, 3, 4, 3, 2, 6, 5 };
	// System.out.println(singleNumber(input));
	// }
}
