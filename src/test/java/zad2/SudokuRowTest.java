package zad2;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuRowTest {

    SudokuBoard sudokuBoard;

    @BeforeEach
    public void boards() {
        sudokuBoard = new SudokuBoard();
    }

    @Test
    void verifyTest() {
        sudokuBoard.generateBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        SudokuRow sudokuRow;
        for (int index = 0; index < 9; index++) {
            sudokuRow = sudokuBoard.getRow(index);
            Assert.assertTrue(sudokuRow.verify());
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i != j) {
                        Assert.assertTrue(sudokuBoard.get(index, i) != sudokuBoard.get(index, j));
                        Assertions.assertTrue(sudokuBoard.get(index, i) > 0);
                        Assertions.assertTrue(sudokuBoard.get(index, i) < 10);
                    }
                }
            }
        }

    }
}