package leetcodeoj;

import org.junit.Test;

/**
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest sum.
 * 
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray
 * [4,−1,2,1] has the largest sum = 6.
 * 
 * More practice: If you have figured out the O(n) solution, try coding another
 * solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {

	public int maxSubArray(int[] A) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
			if (max < sum) {
				max = sum;
			}
			if (sum < 0) {
				sum = 0;
			}
		}
		return max;
	}

	public int divideMethod(int[] A, int left, int right) {
		if (left == right) {
			return A[left];
		}
		int middle = (left + right) / 2;
		int leftmax = divideMethod(A, left, middle);
		int rightmax = divideMethod(A, middle + 1, right);
		int leftSuffixMax = A[middle];
		int temp = 0;
		for (int i = middle; i >= left; i--) {
			temp += A[i];
			if (temp > leftSuffixMax) {
				leftSuffixMax = temp;
			}
		}
		int rightPrefixMax = A[middle + 1];
		temp = 0;
		for (int i = middle + 1; i <= right; i++) {
			temp += A[i];
			if (temp > rightPrefixMax) {
				rightPrefixMax = temp;
			}
		}
		return Math.max(Math.max(leftmax, rightmax), leftSuffixMax
				+ rightPrefixMax);

	}

	@Test
	public void test() {
		int a[] = { -2 };
		System.out.println(divideMethod(a, 0, a.length - 1));
	}

}
