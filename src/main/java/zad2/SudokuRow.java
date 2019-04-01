package zad2;

public class SudokuRow {
    private SudokuField row[]=new SudokuField[9];
    public boolean verify() {
        int count=0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i != j) {
                    if (row[i] == row[j] || row[i].getFieldValue() <0 || row[i].getFieldValue() >10 ) {
                        count++;
                    }
                }
            }
        }
        if(count==0)return true;
        else return false;
    }

    public SudokuField[] getRow() {
        return row;
    }

    public void setRow(SudokuField[] row) {
        this.row = row;
    }
}
