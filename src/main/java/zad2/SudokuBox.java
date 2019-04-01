package zad2;

public class SudokuBox {
    private SudokuField box[][] = new SudokuField[3][3];

    public boolean verify() {
        int count = 0;
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i != j) {
                        if (box[k][i] == box[k][j]) count++;
                        if (box[j][k] == box[i][k]) count++;
                        if (box[j][k].getFieldValue() > 9 || box[j][k].getFieldValue() < 0) count++;
                    }
                }
            }
        }
        if (count == 0) return true;
        else return false;
    }

    public SudokuField[][] getBox() {
        return box;
    }

    public void setBox(SudokuField[][] box) {
        this.box = box;
    }
}
