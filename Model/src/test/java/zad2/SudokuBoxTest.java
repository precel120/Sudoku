package zad2;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuBoxTest {

    SudokuBoard sudoku = new SudokuBoard();
    SudokuBoard sudoku2 = new SudokuBoard();
    SudokuSolver solver = new BacktrackingSudokuSolver();
    SudokuBox sudokuBox;

    @BeforeEach
    public void start() {
        sudoku.generateBoard();
        sudoku2.generateBoard();
        solver.solve(sudoku);
        solver.solve(sudoku2);
    }

    @Test
    public void hashTest1() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                SudokuBox box1= sudoku.getBox(i,j);
                SudokuBox box2= sudoku.getBox(i,j);
                Assertions.assertEquals(box1.hashCode(),box2.hashCode());
            }
        }
    }

    @Test
    public void hashTest2() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++)
            {
                for(int k=0; k<3; k++)
                {
                    for(int l=0; l<3; l++)
                    {
                        if(i!=k && j!=l) {
                            SudokuBox box1= sudoku.getBox(i,j);
                            SudokuBox box2= sudoku.getBox(k,l);
                            Assertions.assertFalse(box1.hashCode()==box2.hashCode());
                        }
                    }
                }
            }
        }
    }


    @Test
    public void EqualsTest1() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++)
            {
                SudokuBox box1= sudoku.getBox(i,j);
                SudokuBox box2= sudoku.getBox(i,j);
                Assertions.assertEquals(box1.hashCode(),box2.hashCode());
                Assertions.assertTrue(box1.equals(box2));
                Assertions.assertTrue(box2.equals(box1));
            }
        }
    }

    @Test
    public void EqualsTest2() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++)
            {
                for(int k=0; k<3; k++)
                {
                    for(int l=0; l<3; l++)
                    {
                        if(i!=k && j!=l) {
                            SudokuBox box1= sudoku.getBox(i,j);
                            SudokuBox box2= sudoku.getBox(k,l);
                            Assertions.assertFalse(box1.equals(box2));
                            Assertions.assertFalse(box2.equals(box1));
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testToString(){
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++)
            {
                SudokuBox box1= sudoku.getBox(i,j);
                SudokuBox box2= sudoku.getBox(i,j);
                Assertions.assertNotNull(box1.toString());
                Assertions.assertNotNull(box2.toString());
                Assertions.assertEquals(box1.toString(),box2.toString());
            }
        }
    }

    @Test
    void verifyTest1() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sudokuBox = sudoku.getBox(i, j);
                Assert.assertTrue(sudokuBox.verify());
            }
        }
    }

    @Test
    void verifyTest2() {
        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i != j) {
                        Assertions.assertFalse(sudoku.get(k,i) == sudoku.get(k,j));
                        Assertions.assertFalse(sudoku.get(i,k) == sudoku.get(j,k));
                        Assertions.assertTrue(sudoku.get(k,i) > 0);
                        Assertions.assertTrue(sudoku.get(k,i) < 10);
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 9; k++) {
                    for (int l = k + 1; l < 9; l++) {
                        Assertions.assertFalse(sudoku.get(i * 3 + (l / 3),j * 3 + (l % 3)) == sudoku.get(i * 3 + (k / 3),j * 3 + (k % 3)));
                    }
                }
            }
        }
    }
}