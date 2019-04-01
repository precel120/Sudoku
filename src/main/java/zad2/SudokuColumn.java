package zad2;

import java.util.Collection;
import java.util.TreeSet;

public class SudokuColumn {
    private SudokuField col[]=new SudokuField[9];
    public boolean verify() {
        int count=0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i != j) {
                        if (col[i] == col[j] || col[i].getFieldValue() <0 || col[i].getFieldValue() >10 ) {
                            count++;
                        }
                    }
                }
            }
        if(count==0)return true;
        else return false;
    }

    public SudokuField[] getCol() {
        return col;
    }

    public void setCol(SudokuField[] col) {
        this.col = col;
    }
}
//obczaic treeset treeset.addAll