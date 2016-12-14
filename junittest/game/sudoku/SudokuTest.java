package game.sudoku;

import org.junit.*;

/**
 * Created by zhaoqc on 2016/12/14.
 */
public class SudokuTest {

    private static int[][] game;
    private long start;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        game = new FinalGenerator().generateFinal();
//        printArray(game);
    }

    @Before
    public void before() {
        start = System.currentTimeMillis();
    }

    @After
    public void after() {
        System.out.println(System.currentTimeMillis()-start);
    }



    @Test
    public void testCheck() {
        Assert.assertTrue(new Check().check(game));
    }

    @Test
    public void testSolve() {
        int[][] game = new int[Sudoku.LENGTH][Sudoku.LENGTH];
        game[0][0] = 8;
        game[1][2] = 3;
        game[1][3] = 6;
        game[2][1] = 7;
        game[2][4] = 9;
        game[2][6] = 2;
        game[3][1] = 5;
        game[3][5] = 7;
        game[4][4] = 4;
        game[4][5] = 5;
        game[4][6] = 7;
        game[5][3] = 1;
        game[5][7] = 3;
        game[6][2] = 1;
        game[6][7] = 6;
        game[6][8] = 8;
        game[7][2] = 8;
        game[7][3] = 5;
        game[7][7] = 1;
        game[8][1] = 9;
        game[8][6] = 4;

        printArray(game);
        Solver solver = new Solver();
        int[][] result = solver.solve(game);
        printArray(result);
        Assert.assertNotNull(result);
        Check check = new Check();
        Assert.assertTrue(check.check(result));

    }

    @Test
    public void testPointerSolve() {
        int[][] game = new int[Sudoku.LENGTH][Sudoku.LENGTH];
        game[0][0] = 8;
        game[1][2] = 3;
        game[1][3] = 6;
        game[2][1] = 7;
        game[2][4] = 9;
        game[2][6] = 2;
        game[3][1] = 5;
        game[3][5] = 7;
        game[4][4] = 4;
        game[4][5] = 5;
        game[4][6] = 7;
        game[5][3] = 1;
        game[5][7] = 3;
        game[6][2] = 1;
        game[6][7] = 6;
        game[6][8] = 8;
        game[7][2] = 8;
        game[7][3] = 5;
        game[7][7] = 1;
        game[8][1] = 9;
        game[8][6] = 4;

        printArray(game);
        PointerSolver solver = new PointerSolver();
        int[][] result = solver.solve(game);
        printArray(result);
        Assert.assertNotNull(result);
        Check check = new Check();
        Assert.assertTrue(check.check(result));

    }

    private static void printArray(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
