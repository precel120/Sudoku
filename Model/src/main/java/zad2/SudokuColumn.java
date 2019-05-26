package zad2;

import java.io.Serializable;
import java.util.List;

public class SudokuColumn extends SudokuSector implements Serializable, Cloneable {
    public SudokuColumn(final List<SudokuField> sudokuField){
        super(sudokuField);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
