package zad2;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuRowTest {

    SudokuBoard sudokuBoard = new SudokuBoard();
    SudokuSolver solver = new BacktrackingSudokuSolver();
    SudokuRow sudokuRow;

    @BeforeEach
    public void start() {
        sudokuBoard.generateBoard();
        solver.solve(sudokuBoard);
    }

    @Test
    public void EqualsTest1() {
        for(int i=0; i<9; i++) {
            SudokuRow row1= sudokuBoard.getRow(i);
            SudokuRow row2= sudokuBoard.getRow(i);
            Assertions.assertEquals(row1.hashCode(),row2.hashCode());
            Assertions.assertTrue(row1.equals(row2));
            Assertions.assertTrue(row2.equals(row1));
        }
    }

    @Test
    public void EqualsTest2() {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++)
            {
                if(i!=j) {
                    SudokuRow row1= sudokuBoard.getRow(i);
                    SudokuRow row2= sudokuBoard.getRow(j);
                    Assertions.assertFalse(row1.equals(row2));
                    Assertions.assertFalse(row2.equals(row1));
                }
            }
        }
    }

    @Test
    public void hashTest1() {
        for(int i=0; i<9; i++) {
            SudokuRow row1= sudokuBoard.getRow(i);
            SudokuRow row2= sudokuBoard.getRow(i);
            Assertions.assertEquals(row1.hashCode(),row2.hashCode());
        }
    }

    @Test
    public void hashTest2() {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++)
            {
                if(i!=j) {
                    SudokuRow row1= sudokuBoard.getRow(i);
                    SudokuRow row2= sudokuBoard.getRow(j);
                    Assertions.assertFalse(row1.hashCode()==row2.hashCode());
                }
            }
        }
    }

    @Test
    void verifyTest1() {
        for (int index = 0; index < 9; index++) {
            sudokuRow = sudokuBoard.getRow(index);
            List<SudokuField> tmp = sudokuRow.getSector();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i != j) {
                        Assert.assertTrue(tmp.get(i).getFieldValue() != tmp.get(j).getFieldValue());
                        Assertions.assertTrue(tmp.get(i).getFieldValue() > 0 || tmp.get(i).getFieldValue() < 10);
                    }
                }
            }
        }

    }

    @Test
    void verifyTest2() {
        for (int i = 0; i < 9; i++) {
            sudokuRow = sudokuBoard.getRow(i);
            List<SudokuField> tmp = sudokuRow.getSector();
            Assert.assertTrue(sudokuRow.verify());
        }
    }
}