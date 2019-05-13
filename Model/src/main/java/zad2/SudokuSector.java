package zad2;

import java.io.Serializable;
import java.lang.Object;
import java.util.List;

import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuSector implements Serializable, Cloneable {
    private List<SudokuField> sudokuField;

    public SudokuSector(final List<SudokuField> sudokuField) {
        this.sudokuField = sudokuField;
    }

    public boolean verify() {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i != j) {
                    if (sudokuField.get(i).getFieldValue() == sudokuField.get(j).getFieldValue() || sudokuField.get(i).getFieldValue() < 0 || sudokuField.get(i).getFieldValue() > 9) {
                        count++;
                    }
                }
            }
        }
        if (count == 0) {
            return true;
        } else return false;
    }

    public List<SudokuField> getSector() {
        return sudokuField;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("Fields",this.sudokuField).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sudokuField);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuSector that = (SudokuSector) o;
        return Objects.equal(sudokuField, that.sudokuField);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}