package leetcodeoj;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * For example, Given the following matrix:
 * 
 * [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
 * 
 * You should return [1,2,3,6,9,8,7,4,5].
 * 
 * 
 */
public class SpiralMatrix {
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return result;
		}
		int rowLow = 0;
		int rowHigh = matrix.length - 1;
		int colLow = 0;
		int colHigh = matrix[0].length - 1;
		while (rowLow <= rowHigh && colLow <= colHigh) {
			for (int i = colLow; i <= colHigh; i++) {
				result.add(matrix[rowLow][i]);
			}
			rowLow++;
			if (rowLow > rowHigh) {
				break;
			}
			for (int j = rowLow; j <= rowHigh; j++) {
				result.add(matrix[j][colHigh]);
			}
			colHigh--;
			if (colLow > colHigh) {
				break;
			}
			for (int i = colHigh; i >= colLow; i--) {
				result.add(matrix[rowHigh][i]);
			}
			rowHigh--;
			for (int j = rowHigh; j >= rowLow; j--) {
				result.add(matrix[j][colLow]);
			}
			colLow++;

		}
		return result;
	}

	
	@Test
	public void test() {
		int matrix[][] = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 },
				{ 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 } };
		System.out.println(spiralOrder(matrix));
	}
}
