package zad2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SudokuBoard {
    private SudokuField[][] board;

    public SudokuBoard() {
        board = new SudokuField[9][9];

        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j <9 ; j++) {
                board[i][j]=new SudokuField();
            }
        }
    }

    public SudokuField[][] getBoard() {
        SudokuField[][] pom;
        pom = board;
        return pom;
    }

    private boolean checkBoard() {
        int count1 = 0, count2 = 0, count3 = 0;
        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i != j) {
                        if (board[k][i] == board[k][j] || board[k][i].getFieldValue() < 0 || board[k][i].getFieldValue() > 10) {
                            count1++;
                        }
                        if (board[i][k] == board[j][k]) {
                            count2++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 9; k++) {
                    for (int l = k + 1; l < 9; l++) {
                        if (board[i * 3 + (l / 3)][j * 3 + (l % 3)] == board[i * 3 + (k / 3)][j * 3 + (k % 3)]) {
                            count3++;
                        }
                    }
                }
            }
        }
        if (count1 == 0 && count2 == 0 && count3 == 0) {
            return true;
        } else return false;
    }

    private boolean containsInRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i].getFieldValue() == number) {
                return true;
            }
        }
        return false;
    }

    private boolean containsInCol(int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col].getFieldValue() == number) {
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
                if (board[i][j].getFieldValue() == number) {
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j].setFieldValue(0);
            }
        }
        List<Integer> pom = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            pom.add(i);
        }
        Collections.shuffle(pom);
        for (int i = 0; i < 9; i++) {
            board[0][i].setFieldValue(pom.get(i) + 1);
        }
    }

    public int get(int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9) throw new IllegalArgumentException("liczby poza zakresem");
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        if (x < 0 || x > 9 || y < 0 || y > 9 || value > 9 || value < 0)
            throw new IllegalArgumentException("liczby poza zakresem");
        board[x][y].setFieldValue(value);
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getFieldValue() + " ");
            }
            System.out.println();
        }
    }

    public SudokuRow getRow(int y){
        if(y>9||y<0) throw new IllegalArgumentException("liczba poza zakresem");
        SudokuRow row=new SudokuRow();
        SudokuField[] sudokuField = new SudokuField[9];
        for(int i=0; i<9; i++)
        {
           sudokuField[i]=this.board[y][i];
        }
        row.setRow(sudokuField);
        return row;
    }

    public SudokuColumn getColumn(int x){
        if(x>9||x<0) throw new IllegalArgumentException("liczby poza zakresem");
        SudokuColumn sudokuColumn=new SudokuColumn();
        SudokuField[] sudokuField = new SudokuField[9];
        for(int i =0;i<9;i++){
            sudokuField[i]=this.board[i][x];
            System.out.print(sudokuField[i].getFieldValue());
        }
        sudokuColumn.setCol(sudokuField);
        return sudokuColumn;
    }

//???????
    public SudokuBox getBox(int x,int y){
        if(x>9||x<0||y>9||y<0) throw new IllegalArgumentException("liczby poza zakresem");
        int r = x - x % 3;
        int c = y - y % 3;
        SudokuField[][] sudokuField = new SudokuField[3][3];
        SudokuBox sudokuBox = new SudokuBox();
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                sudokuField[i][j] = board[i][j];
            }
        }
        sudokuBox.setBox(sudokuField);
        return sudokuBox;
    }
}