package zad2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BacktrackingSudokuSolverTest {

    SudokuBoard board=new SudokuBoard();

    @Test
    void solveTest1() {
        board.generateBoard();
        BacktrackingSudokuSolver solver=new BacktrackingSudokuSolver();
        Assertions.assertTrue(solver.solve(board));
    }

    @Test
    void solveTest2() {
        board.generateBoard();
        BacktrackingSudokuSolver solver=new BacktrackingSudokuSolver();
        solver.solve(board);
        List<List<SudokuField>> tmp2=board.getBoard();

        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i != j) {
                        Assertions.assertFalse(tmp2.get(k).get(i) == tmp2.get(k).get(j));
                        Assertions.assertFalse(tmp2.get(i).get(k) == tmp2.get(j).get(k));
                        Assertions.assertTrue(tmp2.get(k).get(i).getFieldValue() > 0);
                        Assertions.assertTrue(tmp2.get(k).get(i).getFieldValue() < 10);
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 9; k++) {
                    for (int l = k + 1; l < 9; l++) {
                        Assertions.assertFalse(tmp2.get(i * 3 + (l / 3)).get(j * 3 + (l % 3)) == tmp2.get(i * 3 + (k / 3)).get(j * 3 + (k % 3)));
                    }
                }
            }
        }

    }
}