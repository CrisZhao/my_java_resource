package leetcodeoj;

import java.util.Arrays;

import org.junit.Test;

/**
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this
 * problem.
 * 
 * 
 * Follow up: A rather straight forward solution is a two-pass algorithm using
 * counting sort. First, iterate the array counting number of 0's, 1's, and 2's,
 * then overwrite array with total number of 0's, then 1's and followed by 2's.
 * 
 * Could you come up with an one-pass algorithm using only constant space?
 * 
 */
public class SortColors {

	public void sortColors(int[] A) {

		int red = -1;
		int blue = A.length;
		int white = 0;
		while (white < blue) {
			int value = A[white];
			if (value == 0) {
				A[white++] = A[++red];
				A[red] = 0;
			} else if (value == 2) {
				A[white] = A[--blue];
				A[blue] = 2;
			} else {
				white++;
			}
		}
	}

	@Test
	public void test() {
		int A[] = { 2, 2 };
		sortColors(A);
		System.out.println(Arrays.toString(A));
	}
}
