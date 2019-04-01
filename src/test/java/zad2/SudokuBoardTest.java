package zad2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SudokuBoardTest {
    SudokuBoard sudokuBoard;
    SudokuBoard sudokuBoard2;


    @BeforeEach
    public void boards() {
        sudokuBoard = new SudokuBoard();
        sudokuBoard2 = new SudokuBoard();
    }


    @Test
    public void checkGet1() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> sudokuBoard.get(-1, -2));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
    }

    @Test
    public void checkGet2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> sudokuBoard.get(9, -2));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
    }

    @Test
    public void checkGet3() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> sudokuBoard.get(20, 10));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
    }

    @Test
    public void checkGet4() {
        sudokuBoard.generateBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        SudokuField tab[][] = sudokuBoard.getBoard();
        Assertions.assertTrue(tab[5][5].getFieldValue() == sudokuBoard.get(5, 5));
    }

    @Test
    public void checkSet1() {
        sudokuBoard.generateBoard();
        sudokuBoard.set(5, 5, 1);
        Assertions.assertTrue(sudokuBoard.get(5, 5) == 1);
    }

    @Test
    public void checkSet2() {
        sudokuBoard.generateBoard();
        sudokuBoard.set(0, 5, 5);
        Assertions.assertTrue(sudokuBoard.get(0, 5) == 5);
    }

    @Test
    public void checkSet3() {
        sudokuBoard.generateBoard();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> sudokuBoard.set(0, 5, -10));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
        Assertions.assertFalse(sudokuBoard.get(0, 5) == -10);
    }

    @Test
    public void checkSet4() {
        sudokuBoard.generateBoard();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> sudokuBoard.set(-20, 5, -10));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
    }

    @Test
    public void checkSet5() {
        sudokuBoard.generateBoard();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> sudokuBoard.set(10, 5, 1));
        Assert.assertTrue(thrown.getMessage().contains("liczby poza zakresem"));
    }

    /*
    @Test
    public void Tescik(){
        sudokuBoard.generateBoard();
        sudokuBoard.printBoard();
        System.out.println();
        sudokuBoard.getColumn(1);
    }
    */

}