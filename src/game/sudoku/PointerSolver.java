package game.sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoqc on 2016/12/14.
 */
public class PointerSolver extends AbstractSolver {
    private static class Entry {
        int r;
        int c;
        List<Integer> candidates = null;

        public Entry(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int[][] solve(int[][] game) {
        List<Entry> points = searchPoints(game);
        int index = 0;
        int count = 0;
        while (index < points.size()) {
            if (index < 0) {
                return null;
            }

            count++;
            Entry entry = points.get(index);
            int i = entry.r;
            int j = entry.c;
            if (game[i][j] != 0) {
                game[i][j] = 0;
            }
            List<Integer> candidate = entry.candidates;
            if (candidate == null) {
                candidate = findCandidate(game, i, j);
                entry.candidates = candidate;
            }
            if (candidate.isEmpty()) {
                entry.candidates = null;
                game[i][j] = 0;
                index--;
            } else {
                int value = candidate.remove(0);
                game[i][j] = value;
                index++;
            }
        }
        System.out.println(count);
        return game;
    }


    private List<Entry> searchPoints(int[][] game) {
        List<Entry> points = new ArrayList<Entry>(64);
        for (int i = 0; i < Sudoku.LENGTH; i++) {
            for (int j = 0; j < Sudoku.LENGTH; j++) {
                if (game[i][j] == 0) {
                    points.add(new Entry(i, j));
                }
            }
        }
        return points;
    }

}
