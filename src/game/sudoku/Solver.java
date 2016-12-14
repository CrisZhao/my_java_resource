package game.sudoku;

import java.util.List;

/**
 * Created by zhaoqc on 2016/12/14.
 */
public class Solver extends AbstractSolver {
    public int[][] solve(int[][] game) {
        int length = Sudoku.LENGTH;
        int count = 0;
        List<Integer>[][] candidates = new List[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i < 0 || j < 0) {
                    return null;
                }
                count++;
                List<Integer> candidate = candidates[i][j];
                if (candidate == null) {
                    if (game[i][j] != 0) {
                        continue;
                    } else {
                        candidate = findCandidate(game, i, j);
                        candidates[i][j] = candidate;
                    }
                }
                if (candidate.isEmpty()) {
                    candidates[i][j] = null;
                    if (j > 0) {
                        j--;
                    } else {
                        j = length - 1;
                        i--;
                    }
                    game[i][j] = 0;
                    j--;
                } else {
                    game[i][j] = candidate.remove(0);
                }
            }
        }
        System.out.println(count);
        return game;
    }



}
