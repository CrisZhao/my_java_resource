package leetcodeoj;

import org.junit.Test;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 */
public class MinPathSum {
	
	public int minPathSum(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;
		int dp[] = new int[n + 1];
		for (int i = 2; i < dp.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[1] = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dp[j + 1] = Math.min(dp[j], dp[j + 1]) + grid[i][j];
			}
		}

		return dp[n];
	}

	public int minPathSum1(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;
		for (int i = 1; i < m; i++) {
			grid[i][0] += grid[i - 1][0];
		}
		for (int i = 1; i < n; i++) {
			grid[0][i] += grid[0][i - 1];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
			}
		}

		return grid[m - 1][n - 1];
	}

	@Test
	public void test() {
		int grid[][] = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println(minPathSum1(grid));
	}
}
