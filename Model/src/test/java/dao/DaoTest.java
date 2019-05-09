package dao;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import zad2.BacktrackingSudokuSolver;
import zad2.SudokuBoard;
import zad2.SudokuSolver;

import java.io.IOException;

public class DaoTest {

    @Test
    public void readAndWriteTest() {
        Dao<SudokuBoard> dao = new FileSudokuBoardDao("3.bin");
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuBoard sudokuBoardTest = new SudokuBoard();
        sudokuBoard.generateBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoardTest.set(i, j, sudokuBoard.get(i, j));
            }
        }
        dao.write(sudokuBoard);
        SudokuBoard sudokuBoard1 = dao.read();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Assertions.assertEquals(sudokuBoardTest.get(i, j), sudokuBoard1.get(i, j));
            }
        }
    }
}
