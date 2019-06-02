package zad2;

import exceptions.SudokuBoardException;
import exceptions.SudokuFieldException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("langModel");

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
        SudokuFieldException thrown = assertThrows(SudokuFieldException.class,
                ()->field.setFieldValue(-3));
        Assert.assertTrue(thrown.getMessage().contains(resourceBundle.getObject("argument").toString()));
        Assertions.assertFalse(field.getFieldValue() == -3);
    }
}