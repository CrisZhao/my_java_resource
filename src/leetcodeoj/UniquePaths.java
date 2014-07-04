package leetcodeoj;

import org.junit.Test;

/**
 * A robot is located at the top-left corner of a m x n grid (grid[0][0]).
 * <p/>
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (grid[m][n]).
 * <p/>
 * How many possible unique paths are there?
 * <p/>
 * <p/>
 * Note: m and n will be at most 100.
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {

        int dp[] = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public int backtrack(int r, int c, int m, int n) {
        if (r == m && c == n)
            return 1;
        if (r > m || c > n)
            return 0;
        return backtrack(r + 1, c, m, n) + backtrack(r, c + 1, m, n);
    }

    private int backTrackMemoization(int r, int c, int m, int n, int mat[][]) {
        if (r == m & c == n) {
            return 1;
        }
        if (r > m || c > n) {
            return 0;
        }
        if (mat[r + 1][c] == -1) {
            mat[r + 1][c] = backTrackMemoization(r + 1, c, m, n, mat);
        }
        if (mat[r][c + 1] == -1) {
            mat[r][c + 1] = backTrackMemoization(r, c + 1, m, n, mat);
        }
        return mat[r + 1][c] + mat[r][c + 1];
    }

    public int btMemoization(int m, int n) {
        int mat[][] = new int[m + 2][n + 2];
        for (int i = 0; i < m + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                mat[i][j] = -1;
            }
        }
        return backTrackMemoization(1, 1, m, n, mat);
    }

    public int dpBottomUp(int m, int n) {
        int mat[][] = new int[m + 2][n + 2];
        mat[m][n + 1] = 1;
        for (int i = m; i > 0; i--) {
            for (int j = n; j > 0; j--) {
                mat[i][j] = mat[i + 1][j] + mat[i][j + 1];
            }
        }
        return mat[1][1];
    }

    @Test
    public void test() {
        int result = dpBottomUp(23, 12);
        System.out.println(result);
    }

}
