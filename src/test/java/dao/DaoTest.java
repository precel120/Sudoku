package dao;

import org.junit.jupiter.api.Test;
import zad2.BacktrackingSudokuSolver;
import zad2.SudokuBoard;
import zad2.SudokuSolver;

public class DaoTest {
    private Dao<SudokuBoard> dao = new FileSudokuBoardDao("1.bin");
    @Test
    public void tescik(){
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.generateBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        sudokuBoard.printBoard();
        System.out.println();
        dao.write(sudokuBoard);
        SudokuBoard sudokuBoard1 = dao.read();
        sudokuBoard1.printBoard();
    }
}
