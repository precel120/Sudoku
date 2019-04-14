package zad2;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class SudokuField{
    private int value=0;

    public SudokuField(int value) {
        this.value = value;
    }

    public SudokuField() {
    }

    public int getFieldValue() {
        return value;
    }
    public void setFieldValue(int value) {
        this.value=value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuField that = (SudokuField) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }
}