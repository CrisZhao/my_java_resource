package leetcodeoj;

import org.junit.Test;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
 * Do it in place.
 * 
 * 
 * Follow up: Did you use extra space? A straight forward solution using O(mn)
 * space is probably a bad idea. A simple improvement uses O(m + n) space, but
 * still not the best solution. Could you devise a constant space solution?
 * 
 */
public class SetMatrixZeroes {
	public void setZeroes(int[][] matrix) {
		if(matrix.length==0||matrix[0].length==0) {
			return;
		}
		int m=matrix.length;
		int n = matrix[0].length;
		boolean firstRow = false;
		boolean firstCol = false;
		for (int f : matrix[0]) {
			if(f==0) {
				firstRow=true;
				break;
			}
		}
		for (int i=0;i<m;i++) {
			if(matrix[i][0]==0) {
				firstCol=true;
				break;
			}
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if(matrix[i][j]==0) {
					matrix[0][j]=0;
					matrix[i][0]=0;
				}
			}
		}
		for (int i = 1; i < m; i++) {
			if(matrix[i][0]==0) {
				for (int j = 1; j < n; j++) {
					matrix[i][j]=0;
				}
			}
		}
		for (int i = 1; i < n; i++) {
			if(matrix[0][i]==0) {
				for (int j = 1; j < m; j++) {
					matrix[j][i]=0;
				}
			}
		}
		if(firstRow) {
			for (int i = 0; i < n; i++) {
				matrix[0][i]=0;
			}
		}
		if(firstCol) {
			for (int i = 0; i < m; i++) {
				matrix[i][0]=0;
			}
		}

	}
	
	@Test
	public void test() {
		int matrix[][]={{0,0,0,5},{1,2,3,4},{0,2,3,4},{1,2,1,2},{0,0,1,1}};
		setZeroes(matrix);
		System.out.println(matrix);
	}
}
