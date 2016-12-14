package game.sudoku;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zhaoqc on 2016/12/14.
 */
public abstract class AbstractSolver {
    public abstract int[][] solve(int[][] game);

    protected List<Integer> findCandidate(int[][] game, int row, int col) {
        HashSet<Integer> exclude = Sets.newHashSet();
        for (int i = 0; i < Sudoku.LENGTH; i++) {
            int val = game[i][col];
            if (val == 0) {
                continue;
            }
            exclude.add(val);
        }
        for (int j = 0; j < Sudoku.LENGTH; j++) {
            int val = game[row][j];
            if (val == 0) {
                continue;
            }
            exclude.add(val);
        }
        if (exclude.size() == Sudoku.LENGTH) {
            return Lists.newArrayList();
        }
        int cStart = col / 3 * 3;
        int rStart = row / 3 * 3;
        for (int i = 0; i < Sudoku.LENGTH; i++) {
            int val = game[rStart + i / 3][cStart + i % 3];
            if (val == 0) {
                continue;
            }
            exclude.add(val);
        }
        if (exclude.size() == Sudoku.LENGTH) {
            return Lists.newArrayList();
        }
        List<Integer> numbers = Lists.newArrayList(Sudoku.NUMBERS);
        numbers.removeAll(exclude);
        Collections.shuffle(numbers);
        return numbers;
    }
}
