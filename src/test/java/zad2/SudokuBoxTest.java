package zad2;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoxTest {

    SudokuBoard sudoku;


    @BeforeEach
    public void boards() {
        sudoku = new SudokuBoard();
    }

    @Test
    void verifyTest() {
        sudoku.generateBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudoku);
        SudokuField board[][] = sudoku.getBoard();
        SudokuBox sudokuBox;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sudokuBox = sudoku.getBox(i, j);
                Assert.assertTrue(sudokuBox.verify());
            }
        }
        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i != j) {
                        Assertions.assertFalse(board[k][i] == board[k][j]);
                        Assertions.assertFalse(board[i][k] == board[j][k]);
                        Assertions.assertTrue(board[k][i].getFieldValue() > 0);
                        Assertions.assertTrue(board[k][i].getFieldValue() < 10);
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 9; k++) {
                    for (int l = k + 1; l < 9; l++) {
                        Assertions.assertFalse(board[i * 3 + (l / 3)][j * 3 + (l % 3)] == board[i * 3 + (k / 3)][j * 3 + (k % 3)]);
                    }
                }
            }
        }
    }
}