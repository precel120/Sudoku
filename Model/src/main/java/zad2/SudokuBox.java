package zad2;


import java.io.Serializable;
import java.util.List;

public class SudokuBox extends SudokuSector implements Serializable, Cloneable {
    public SudokuBox(final List<SudokuField> sudokuField){
        super(sudokuField);
    }
}