package zad2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    void getAndSetTest1() {
        SudokuField field = new SudokuField();
        field.setFieldValue(3);
        Assertions.assertTrue(field.getFieldValue() == 3);
    }

    @Test
    void getAndSetTest2() {
        SudokuField field = new SudokuField();
        field.setFieldValue(30);
        Assertions.assertTrue(field.getFieldValue() == 30);
    }

    @Test
    void getAndSetTest() {
        SudokuField field = new SudokuField();
        field.setFieldValue(-3);
        Assertions.assertTrue(field.getFieldValue() == -3);
    }
}