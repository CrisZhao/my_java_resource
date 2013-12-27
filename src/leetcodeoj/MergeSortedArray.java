package leetcodeoj;

import java.util.Arrays;

import org.junit.Test;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * 
 * Note: You may assume that A has enough space to hold additional elements from
 * B. The number of elements initialized in A and B are m and n respectively.
 * 
 * 
 */
public class MergeSortedArray {
	public void merge(int A[], int m, int B[], int n) {
		int a = m - 1;
		int b = n - 1;
		for (int index = n + m - 1; index >= 0; index--) {

			if (a < 0) {
				A[index] = B[b--];
			} else if (b < 0) {
				A[index] = A[a--];
			} else if (A[a] <= B[b]) {
				A[index] = B[b--];
			} else {
				A[index] = A[a--];
			}
		}
	}

	@Test
	public void test() {
		int a[] = { 1, 3, 5, 6, 0, 0, 0, 0, 0, 0, 0 };
		int b[] = { 2, 4, 5, 6 };
		merge(a, 4, b, 4);
		System.out.println(Arrays.toString(a));
	}

}
