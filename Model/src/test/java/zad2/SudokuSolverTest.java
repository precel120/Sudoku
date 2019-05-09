package zad2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class SudokuSolverTest {
    SudokuBoard board;
    SudokuSolver solver;

    @BeforeEach
    void boards() {
        board=new SudokuBoard();
        board.generateBoard();
        solver=new BacktrackingSudokuSolver();
        solver.solve(board);
    }

    @Test
    void solveRowsTest() {
        for(int i=0; i<9; i++)
        {
            SudokuRow row=board.getRow(i);
            Assertions.assertTrue(row.verify());
        }
    }

    @Test
    void solveColTest(){
        for(int i=0; i<9; i++) {
            SudokuColumn col = board.getColumn(i);
            Assertions.assertTrue(col.verify());
        }
    }

    @Test
    void solveBoxTest() {
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                SudokuBox box=board.getBox(i,j);
                Assertions.assertTrue(box.verify());
            }
        }
    }
}