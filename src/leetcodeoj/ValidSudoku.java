package leetcodeoj;

/**
 * Determine if a Sudoku is valid.
 * <p/>
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * <p/>
 * <p/>
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {

        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] blocks = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int p = board[i][j] - '1';
                if (rows[i][p] == 1 || cols[j][p] == 1 || blocks[i - i % 3 + j / 3][p] == 1) {
                    return false;
                }
                rows[i][p] = cols[j][p] = blocks[i - i % 3 + j / 3][p] = 1;
            }
        }

        return true;
    }


}
