package leetcodeoj;

import org.junit.Test;

/**
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: Could you do this in-place?
 * 
 */
public class RotateImage {
	public void rotate(int[][] matrix) {

		int length = matrix.length;
		if (length == 0 || length == 1) {
			return;
		}
		int temp;
		for (int i = 0; i < length; i++) {
			for (int j = i; j < length; j++) {
				temp=matrix[i][j];
				matrix[i][j]=matrix[j][i];
				matrix[j][i]=temp;

			}
		}
		for (int i = 0; i <length; i++) {
			for (int j = 0; j < length/2; j++) {
				temp=matrix[i][j];
				matrix[i][j]=matrix[i][length-j-1];
				matrix[i][length-j-1]=temp;
			}
		}
	}
	
	
	
	@Test
	public void test() {
		int matrix[][]={{1,2,3},{4,5,6},{7,8,9}};
		rotate(matrix);
		System.out.println(matrix);
	}
}
