package zad2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BacktrackingSudokuSolverTest {

    @Test
    void solveTest1() {
        SudokuBoard board = new SudokuBoard();
        board.generateBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
    }

    @Test
    void solveTest2() {
        SudokuBoard board = new SudokuBoard();
        board.generateBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        SudokuField tmp2[][] = board.getBoard();

        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i != j) {
                        Assertions.assertFalse(tmp2[k][i] == tmp2[k][j]);
                        Assertions.assertFalse(tmp2[i][k] == tmp2[j][k]);
                        Assertions.assertTrue(tmp2[k][i].getFieldValue() > 0);
                        Assertions.assertTrue(tmp2[k][i].getFieldValue() < 10);
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 9; k++) {
                    for (int l = k + 1; l < 9; l++) {
                        Assertions.assertFalse(tmp2[i * 3 + (l / 3)][j * 3 + (l % 3)] == tmp2[i * 3 + (k / 3)][j * 3 + (k % 3)]);
                    }
                }
            }
        }

    }
}

