package leetcodeoj;

import org.junit.Test;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right. The first integer of each
 * row is greater than the last integer of the previous row. For example,
 * 
 * Consider the following matrix:
 * 
 * [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ]
 * 
 * Given target = 3, return true.
 * 
 * 
 */
public class SearchMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {

		int rows = matrix.length;
		int cols = matrix[0].length;
		int high = rows * cols - 1;
		return binarySearch(matrix, 0, high, cols, target);
	}

	private boolean binarySearch(int[][] matrix, int low, int high, int cols,
			int target) {
		if (low > high) {
			return false;
		}
		int middle = (low + high) / 2;
		int midvalue = matrix[middle / cols][middle % cols];
		if (midvalue == target) {
			return true;
		}
		if (target > midvalue) {
			return binarySearch(matrix, middle + 1, high, cols, target);
		} else {
			return binarySearch(matrix, low, middle - 1, cols, target);
		}
	}

	@Test
	public void test() {
		int matrix[][] = { { 1 } };
		System.out.println(searchMatrix(matrix, 1));
	}
}
