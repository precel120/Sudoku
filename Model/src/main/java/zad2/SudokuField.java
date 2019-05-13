package zad2;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Comparator;

public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {
    private int value = 0;

    public SudokuField(int value) {
        this.value = value;
    }

    public SudokuField() {
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuField that = (SudokuField) o;
        return value == that.value;
    }

    public static Comparator<SudokuField> SudokuCompare = new Comparator<SudokuField>() {
        public int compare(SudokuField o1, SudokuField o2) {
            return o1.compareTo(o2);
        }
    };

    public int compareTo(SudokuField o) {
        if (this.value == o.value) return 0;
        if (this.value > o.value) return 1;
        else return -1;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("Value", this.value).toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}