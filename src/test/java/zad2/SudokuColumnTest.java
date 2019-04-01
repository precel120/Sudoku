package zad2;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuColumnTest {

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
        SudokuColumn sudokuColumn;
        for (int index = 0; index < 9; index++) {
        sudokuColumn = sudokuBoard.getColumn(index);
        Assert.assertTrue(sudokuColumn.verify());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i != j) {
                    Assert.assertTrue(sudokuBoard.get(i, index) != sudokuBoard.get(j, index));
                    Assertions.assertTrue(sudokuBoard.get(i, index) > 0);
                    Assertions.assertTrue(sudokuBoard.get(i, index) < 10);
                }
            }
        }
    }
}
}