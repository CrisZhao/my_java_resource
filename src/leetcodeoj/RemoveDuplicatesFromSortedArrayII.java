package leetcodeoj;

import java.util.Arrays;

import org.junit.Test;

/**
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most
 * twice?
 * 
 * For example, Given sorted array A = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 * 
 * 
 */
public class RemoveDuplicatesFromSortedArrayII {
	public int removeDuplicates1(int[] A) {
		int n = A.length;
		if (n == 0) {
			return 0;
		}
		int index = 1;
		boolean duplicate = false;
		int cur = 1;
		while (cur < n) {
			if (A[cur] == A[cur - 1]) {
				if (duplicate) {
					cur++;
					continue;
				}
				duplicate = true;
			} else {
				duplicate = false;
			}
			A[index++] = A[cur++];
		}
		return index;
	}

	@Test
	public void test() {
		int[] a = { 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6 };
		System.out.println(removeDuplicates1(a));
		System.out.println(Arrays.toString(a));
	}

}
