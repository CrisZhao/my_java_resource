package game.sudoku;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by zhaoqc on 2016/12/14.
 */
public class Check {
    public boolean check(int[][] game) {
        for (int i=0;i<Sudoku.LENGTH;i++) {
            if(!(checkRow(game, i) && checkCol(game, i) && checkNine(game, i))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRow(int[][] game, int row) {
        if(row<0||row>=Sudoku.LENGTH) {
            return false;
        }
        Set<Integer> cache = Sets.newHashSet();
        int sum = 0;
        for (int val : game[row]) {
            if(val == 0) {
                return false;
            }
            if(!cache.add(val)) {
                return false;
            }
            sum+=val;
        }
        if(sum!=45) {
            return false;
        }
        return true;
    }

    private boolean checkCol(int[][] game, int col) {
        if(col<0||col>=Sudoku.LENGTH) {
            return false;
        }
        Set<Integer> cache = Sets.newHashSet();
        int sum = 0;
        for (int i=0; i<Sudoku.LENGTH; i++) {
            int val = game[i][col];
            if(val == 0) {
                return false;
            }
            if(!cache.add(val)) {
                return false;
            }
            sum+=val;
        }
        if(sum!=45) {
            return false;
        }
        return true;
    }

    private boolean checkNine(int[][] game, int index) {
        if(index<0||index>=Sudoku.LENGTH) {
            return false;
        }
        int startR = index%3*3;
        int startC = index/3*3;
        Set<Integer> cache = Sets.newHashSet();
        int sum = 0;
        for (int i=0; i<Sudoku.LENGTH; i++) {
            int val = game[startR+i%3][startC+i/3];
            if(val == 0) {
                return false;
            }
            if(!cache.add(val)) {
                return false;
            }
            sum+=val;
        }
        if(sum!=45) {
            return false;
        }
        return true;
    }


}
