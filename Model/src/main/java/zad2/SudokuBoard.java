package zad2;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import exceptions.SudokuBoardException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.*;


public class SudokuBoard implements Serializable, Cloneable {
    private List<List<SudokuField>> board;

    public SudokuBoard(SudokuBoard sudoku) {
        board = Arrays.asList(new List[9]);
        for (int i = 0; i < 9; i++) {
            board.set(i, Arrays.asList(new SudokuField[9]));
        }
        List<List<SudokuField>> tmp=sudoku.getBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.get(i).set(j, tmp.get(i).get(j));
            }
        }
        this.board=sudoku.board;
    }

    public SudokuBoard() {
        board = Arrays.asList(new List[9]);

        for (int i = 0; i < 9; i++) {
            board.set(i, Arrays.asList(new SudokuField[9]));
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.get(i).set(j, new SudokuField());
            }
        }

    }

    public List<List<SudokuField>> getBoard() {
        List<List<SudokuField>> pom;
        pom = board;
        return pom;
    }


    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify()) {
                return false;
            }
            if (!getColumn(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < 2; i ++) {
            for (int j = 0; j < 2; j ++) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean containsInRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (board.get(row).get(i).getFieldValue() == number) {
                return true;
            }
        }
        return false;
    }

    private boolean containsInCol(int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (board.get(i).get(col).getFieldValue() == number) {
                return true;
            }
        }
        return false;
    }

    private boolean containsInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board.get(i).get(j).getFieldValue() == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkBoard(int row, int col, int number) {
        return !(containsInRow(row, number) || containsInCol(col, number) || containsInBox(row, col, number));
    }

    //Wypelnia cala plansze zerami i losuje pierwszy rzad
    public void generateBoard() {
        List<Integer> pom = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            pom.add(i);
        }
        Collections.shuffle(pom);
        for (int i = 0; i < 9; i++) {
            board.get(0).get(i).setFieldValue(pom.get(i) + 1);
        }
    }

    public int get(int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9) throw new SudokuBoardException("liczby poza zakresem");
        return board.get(x).get(y).getFieldValue();
    }

    public void set(int x, int y, int value) {
        if (x < 0 || x > 9 || y < 0 || y > 9 || value > 9 || value < 0)
            throw new SudokuBoardException("liczby poza zakresem");
        board.get(x).get(y).setFieldValue(value);
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.get(i).get(j).getFieldValue() + " ");
            }
            System.out.println();
        }
    }

    public SudokuRow getRow(int y) {
        if (y > 9 || y < 0) throw new SudokuBoardException("liczba poza zakresem");
        List<SudokuField> sudokuField = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            sudokuField.set(i, this.board.get(y).get(i));
        }
        SudokuRow row = new SudokuRow(sudokuField);
        return row;
    }

    public SudokuColumn getColumn(int x) {
        if (x > 9 || x < 0) throw new SudokuBoardException("liczby poza zakresem");
        List<SudokuField> sudokuField = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            sudokuField.set(i, this.board.get(i).get(x));
        }
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuField);
        return sudokuColumn;
    }

    public SudokuBox getBox(int row, int col) {
        if(row>2||row<0||col>2||col<0) throw new SudokuBoardException("liczby poza zakresem");
        List<SudokuField> box = Arrays.asList(new SudokuField[9]);
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box.set(index++, board.get(row * 3 + i).get(col * 3 + j));
            }
        }
        return new SudokuBox(box);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equal(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("board", board).toString();
    }
}