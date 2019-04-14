package zad2;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SudokuColumnTest {

    SudokuBoard sudokuBoard = new SudokuBoard();
    SudokuBoard sudokuBoard2 = new SudokuBoard();

    SudokuSolver solver = new BacktrackingSudokuSolver();
    SudokuColumn sudokuCol;

    @BeforeEach
    public void start() {
        sudokuBoard.generateBoard();
        sudokuBoard2.generateBoard();
        solver.solve(sudokuBoard);
        solver.solve(sudokuBoard2);
    }

    @Test
    public void hashTest1() {
        for(int i=0; i<9; i++) {
            SudokuColumn col1= sudokuBoard.getColumn(i);
            SudokuColumn col2= sudokuBoard.getColumn(i);
            SudokuColumn col3= sudokuBoard2.getColumn(i);
            Assertions.assertEquals(col1.hashCode(),col2.hashCode());
            Assertions.assertFalse(col1.hashCode()==col3.hashCode());

        }
    }

    @Test
    public void hashTest2() {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++)
            {
                if(i!=j) {
                    SudokuColumn col1= sudokuBoard.getColumn(i);
                    SudokuColumn col2= sudokuBoard.getColumn(j);
                    SudokuColumn col3= sudokuBoard2.getColumn(i);
                    Assertions.assertFalse(col1.hashCode()==col3.hashCode());
                    Assertions.assertFalse(col1.hashCode()==col2.hashCode());
                }
            }
        }
    }

    @Test
    public void EqualsTest1() {
        for(int i=0; i<9; i++) {
            SudokuColumn col1= sudokuBoard.getColumn(i);
            SudokuColumn col2= sudokuBoard.getColumn(i);
            Assertions.assertEquals(col1.hashCode(),col2.hashCode());
            Assertions.assertTrue(col1.equals(col2));
            Assertions.assertTrue(col2.equals(col1));
        }
    }

    @Test
    public void EqualsTest2() {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++)
            {
                if(i!=j) {
                    SudokuColumn col1= sudokuBoard.getColumn(i);
                    SudokuColumn col2= sudokuBoard.getColumn(j);
                    Assertions.assertFalse(col1.equals(col2));
                    Assertions.assertFalse(col2.equals(col1));
                }
            }
        }
    }

    @Test
    public void testToString(){
        for(int i=0; i<9; i++) {
            SudokuColumn col1= sudokuBoard.getColumn(i);
            SudokuColumn col2= sudokuBoard.getColumn(i);
            assertNotNull(col1.toString());
            assertNotNull(col2.toString());
            Assertions.assertEquals(col1.toString(),col2.toString());
        }
    }

    @Test
    void verifyTest1() {
        for (int i = 0; i < 9; i++) {
            sudokuCol = sudokuBoard.getColumn(i);
            List<SudokuField> tmp = sudokuCol.getSector();
            Assert.assertTrue(sudokuCol.verify());
        }
    }

    @Test
    void verifyTest2() {
        for (int index = 0; index < 9; index++) {
            sudokuCol = sudokuBoard.getColumn(index);
            List<SudokuField> tmp = sudokuCol.getSector();
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
}
