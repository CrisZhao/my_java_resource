package leetcodeoj;

import org.junit.Test;

/**
 * A robot is located at the top-left corner of a m x n grid (grid[0][0]).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (grid[m][n]).
 * 
 * How many possible unique paths are there?
 * 
 * 
 * Note: m and n will be at most 100.
 * 
 * 
 */
public class UniquePaths {

	public int uniquePaths(int m, int n) {
		if (m == 0 || n == 0) {
			return 0;
		}
		if (m == 1 || n == 1) {
			return 1;
		}
		if (m == 2) {
			return n;
		}
		int dp[] = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = i;
		}
		for (int i = 3; i <= m; i++) {
			for (int j = 0; j < n; j++) {
				dp[j + 1] += dp[j];
			}
		}
		return dp[n];
	}

	@Test
	public void test() {
		int result = uniquePaths(23, 12);
		System.out.println(result);
	}

}
