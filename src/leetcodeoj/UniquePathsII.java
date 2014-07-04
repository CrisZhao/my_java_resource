package leetcodeoj;

import org.junit.Test;

/**
 * Follow up for "Unique Paths":
 * <p/>
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * <p/>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <p/>
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * <p/>
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * <p/>
 * Note: m and n will be at most 100.
 */
public class UniquePathsII {

    public int dpBottomUp(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) {
            return 0;
        }
        int n = obstacleGrid[0].length;
        if (n == 0) {
            return 0;
        }
        int mat[][] = new int[m + 1][n + 1];
        mat[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    mat[i][j] = 0;
                } else {
                    mat[i][j] = mat[i + 1][j] + mat[i][j + 1];
                }
            }
        }
        return mat[0][0];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        if (m == 0) {
            return 0;
        }
        int n = obstacleGrid[0].length;
        if (n == 0) {
            return 0;
        }

        return cal(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1);
    }

    private int cal(int[][] grid, int m, int n) {
        if (m == -1 || n == -1) {
            return 0;
        }
        if (m == 0 || n == 0) {
            return 1;
        }
        if (grid[m][n] == 1) {
            return 0;
        }
        return cal(grid, m - 1, n) + cal(grid, m, n - 1);

    }

    @Test
    public void test() {
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(dpBottomUp(grid));
    }
}
