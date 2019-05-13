package zad2;

import java.io.Serializable;
import java.util.List;

public class SudokuRow extends SudokuSector implements Cloneable, Serializable {
    public SudokuRow(final List<SudokuField> sudokuField){
        super(sudokuField);
    }
}