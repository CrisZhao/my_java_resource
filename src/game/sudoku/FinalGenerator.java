package game.sudoku;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * generate final
 * Created by zhaoqc on 2016/12/14.
 */
public class FinalGenerator {

    public int[][] generateFinal() {
        int length = Sudoku.LENGTH;
        int[][] game = new int[length][length];

        List<Integer>[][] candidates = new List[length][length];

        for (int i = 0; i< length; i++) {
            for(int j =0; j<length; j++) {
                List<Integer> candidate = candidates[i][j];
                if(candidate == null) {
                    candidate = findCandidate(game, i, j);
                    candidates[i][j] = candidate;
                }
                if(candidate.isEmpty()) {
                    candidates[i][j] = null;
                    if(j>0) {
                        j--;
                    } else{
                        j=length-1;
                        i--;
                    }
                    game[i][j] = 0;
                    j--;
                } else {
                    game[i][j] = candidate.remove(0);
                }
            }
        }
        return game;

    }

    private List<Integer> findCandidate(int[][] game, int row, int col) {
        HashSet<Integer> exclude = Sets.newHashSet();
        for (int i=0;i<=row; i++) {
            int val = game[i][col];
            if(val == 0) {
                break;
            }
            exclude.add(val);
        }
        for (int j=0;j<=col; j++) {
            int val = game[row][j];
            if(val == 0) {
                break;
            }
            exclude.add(val);
        }
        int cStart = col/3*3;
        int rStart = row/3*3;
        for (int i =0; i<Sudoku.LENGTH-1;i++) {
            int val = game[rStart+i/3][cStart+i%3];
            if(val == 0) {
                break;
            }
            exclude.add(val);
        }
        List<Integer> numbers = Lists.newArrayList(Sudoku.NUMBERS);
        numbers.removeAll(exclude);
        Collections.shuffle(numbers);
        return numbers;
    }


}
