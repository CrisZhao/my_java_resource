package leetcodeoj;

import org.junit.Test;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 */
public class SearchInRotatedSortedArray {
	public int search(int[] A, int target) {
		if (A == null || A.length == 0) {
			return -1;
		}
		int left = 0;
		int right = A.length - 1;
		int mid;
		while (left <= right) {
			mid = (left + right) / 2;
			if (A[mid] == target) {
				return mid;
			}
			if (A[left] <= A[mid]) {
				if (A[left] <= target && target < A[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (A[mid] < target && target <= A[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}

		}

		return -1;
	}

	@Test
	public void test() {
		int A[] = { 5, 6, 0, 1, 2,3,4 };
		System.out.println(search(A, 3));
	}
}
