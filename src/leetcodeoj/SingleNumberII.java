package leetcodeoj;

import org.junit.Test;

/**
 * Given an array of integers, every element appears three times except for one.
 * Find that single one.
 * 
 * Note: Your algorithm should have a linear runtime complexity. Could you
 * implement it without using extra memory?
 * 
 */
public class SingleNumberII {

	public int singleNumber(int[] A) {
		int bit[] = new int[32];
		for (int i : A) {
			for (int j = 0; j < 32; j++) {
				int rotated = i >> j;
				if (rotated == 0) {
					break;
				}
				bit[j] += rotated & 1;
			}
		}
		int result = 0;
		for (int i = 0; i < 32; i++) {
			result |= (bit[i] % 3) << i;
		}

		return result;
	}
	
	public int singleNumber(int A[], int n) {
	    int ones = 0, twos = 0, threes = 0;
	    for (int i = 0; i < n; i++) {
	        twos |= ones & A[i];
	        ones ^= A[i];
	        threes = ones & twos;
	        ones &= ~threes;
	        twos &= ~threes;
	    }
	    return twos;
	}

	@Test
	public void test() {
		int A[] = { 3,3,3,2};
		System.out.println(singleNumber(A, 3));
	}
}
