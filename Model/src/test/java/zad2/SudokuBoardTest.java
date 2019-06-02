package zad2;

import exceptions.SudokuBoardException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SudokuBoardTest {

    SudokuBoard sudokuBoard;
    SudokuBoard sudokuBoard2;
    SudokuBoard sudokuBoard3;
    SudokuBoard sudokuBoard4;
    SudokuSolver solver = new BacktrackingSudokuSolver();

    @BeforeEach
    public void boards() {
        sudokuBoard = new SudokuBoard();
        sudokuBoard2 = new SudokuBoard();
        sudokuBoard3 = new SudokuBoard();
        sudokuBoard4 = new SudokuBoard();

        sudokuBoard.generateBoard();
        sudokuBoard2.generateBoard();
        sudokuBoard3.generateBoard();
        sudokuBoard4.generateBoard();

        solver.solve(sudokuBoard);
        solver.solve(sudokuBoard2);
        solver.solve(sudokuBoard3);
        solver.solve(sudokuBoard4);
    }

    @Test
    public void hashTest() {

        SudokuBoard sudokuBoard5=(sudokuBoard);

        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                sudokuBoard2.set(i,j,sudokuBoard.get(i,j));
            }
        }

        Assertions.assertTrue(sudokuBoard.hashCode()==sudokuBoard5.hashCode());
        Assertions.assertTrue(sudokuBoard.hashCode()==sudokuBoard2.hashCode());
        Assertions.assertTrue(sudokuBoard3.hashCode()!=sudokuBoard2.hashCode());
        Assertions.assertTrue(sudokuBoard3.hashCode()!=sudokuBoard4.hashCode());
    }

    @Test
    public void EqualsTest1() {
        SudokuBoard sudokuBoard3=(sudokuBoard);

        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                sudokuBoard2.set(i,j,sudokuBoard.get(i,j));
            }
        }

        Assertions.assertTrue(sudokuBoard3.equals(sudokuBoard));
        Assertions.assertTrue(sudokuBoard.equals(sudokuBoard3));
        Assertions.assertTrue(sudokuBoard.equals(sudokuBoard2));
        Assertions.assertTrue(sudokuBoard2.equals(sudokuBoard));
        Assertions.assertTrue(sudokuBoard3.equals(sudokuBoard2));
        Assertions.assertTrue(sudokuBoard2.equals(sudokuBoard3));
        Assertions.assertFalse(sudokuBoard2.equals(sudokuBoard4));
    }

    @Test
    public void EqualsTest2() {
        SudokuBoard sudokuBoard4=(sudokuBoard);

        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                sudokuBoard2.set(i,j,sudokuBoard.get(i,j));
            }
        }

        Assertions.assertTrue(sudokuBoard.hashCode()==sudokuBoard4.hashCode() && sudokuBoard.equals(sudokuBoard4));
        Assertions.assertTrue(sudokuBoard.hashCode()==sudokuBoard4.hashCode() && sudokuBoard4.equals(sudokuBoard));
        Assertions.assertTrue(sudokuBoard.hashCode()==sudokuBoard2.hashCode());
        Assertions.assertTrue(sudokuBoard.equals(sudokuBoard2));
        Assertions.assertTrue(sudokuBoard2.equals(sudokuBoard));
        Assertions.assertTrue(sudokuBoard4.hashCode()==sudokuBoard2.hashCode());
        Assertions.assertTrue(sudokuBoard4.equals(sudokuBoard2));
        Assertions.assertTrue(sudokuBoard2.equals(sudokuBoard4));
    }
    @Test
    public void equalsTest3(){
        SudokuBoard su = new SudokuBoard();
        SudokuBoard su1 = new SudokuBoard();
        Assertions.assertEquals(su.hashCode(), su1.hashCode());
        Assertions.assertTrue(su.equals(su1));
    }

    @Test
    public void testToStringNotNull(){
        assertNotNull(sudokuBoard.toString());
        assertNotNull(sudokuBoard2.toString());
    }

    @Test
    public void testToString(){
        SudokuBoard su = new SudokuBoard();
        SudokuBoard su1 = new SudokuBoard();
        assertEquals(su.toString(),su1.toString());
    }

    @Test
    public void checkGet1() {
        SudokuBoardException thrown = assertThrows(SudokuBoardException.class,
                ()->sudokuBoard.get(-1,-2));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
    }

    @Test
    public void checkGet2() {
        SudokuBoardException thrown = assertThrows(SudokuBoardException.class,
                ()->sudokuBoard.get(9,-2));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
    }

    @Test
    public void checkGet3() {
        SudokuBoardException thrown = assertThrows(SudokuBoardException.class,
                ()->sudokuBoard.get(20,10));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
    }

    @Test
    public void checkGet4() {
        sudokuBoard.generateBoard();
        SudokuSolver solver=new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        List<List<SudokuField>> tab = sudokuBoard.getBoard();
        Assertions.assertTrue(tab.get(5).get(5).getFieldValue()==sudokuBoard.get(5,5));
    }

    @Test
    public void checkSet1() {
        sudokuBoard.generateBoard();
        sudokuBoard.set(5,5, 1);
        Assertions.assertTrue(sudokuBoard.get(5,5)== 1);
    }

    @Test
    public void checkSet2() {
        sudokuBoard.generateBoard();
        sudokuBoard.set(0,5, 5);
        Assertions.assertTrue(sudokuBoard.get(0,5)== 5);
    }

    @Test
    public void checkSet3() {
        sudokuBoard.generateBoard();
        SudokuBoardException thrown = assertThrows(SudokuBoardException.class,
                ()->sudokuBoard.set(0,5,-10));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
        Assertions.assertFalse(sudokuBoard.get(0,5)== -10);
    }

    @Test
    public void checkSet4() {
        sudokuBoard.generateBoard();
        SudokuBoardException thrown = assertThrows(SudokuBoardException.class,
                ()->sudokuBoard.set(-20,5,-10));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
    }

    @Test
    public void checkSet5() {
        sudokuBoard.generateBoard();
        SudokuBoardException thrown = assertThrows(SudokuBoardException.class,
                ()->sudokuBoard.set(10,5,1));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
    }

    @Test
    public void checkCheckBoard() {
        SudokuBoard sudoku3=new SudokuBoard();
        sudoku3.generateBoard();
        SudokuSolver solver=new BacktrackingSudokuSolver();
        solver.solve(sudoku3);
        Assertions.assertTrue(sudoku3.checkBoard());
    }
}