package leetcodeoj;

import org.junit.Test;

/**
 * Given an integer n, generate a square matrix filled with elements from 1 to
 * n2 in spiral order.
 * 
 * For example, Given n = 3,
 * 
 * You should return the following matrix: [
 * 
 * [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ]
 * 
 * ]
 * 
 */
public class SpiralMatrixII {
	public int[][] generateMatrix(int n) {

		int matrix[][] = new int[n][n];
		int left = 0;
		int right = n - 1;
		int low = 0;
		int high = n - 1;
		int index = 1;
		while (left < right && low < high) {
			for (int i = left; i <= right; i++) {
				matrix[low][i] = index++;
			}
			low++;
			for (int i = low; i <= high; i++) {
				matrix[i][right] = index++;
			}
			right--;
			for (int i = right; i >= left; i--) {
				matrix[high][i] = index++;
			}
			high--;
			for (int i = high; i >= low; i--) {
				matrix[i][left] = index++;
			}
			left++;
		}
		if (n % 2 == 1) {
			matrix[n / 2][n / 2] = index;
		}

		return matrix;
	}
	
	@Test
	public void test() {
		int matrix[][]=generateMatrix(4);
		System.out.println(matrix);
	}
}
