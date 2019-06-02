package zad2;

import exceptions.SudokuFieldException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

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
        ResourceBundle resourceBundle = ResourceBundle.getBundle("langModel_pl");
        SudokuField field = new SudokuField();
        SudokuFieldException thrown = assertThrows(SudokuFieldException.class,
                ()->field.setFieldValue(30));
        Assert.assertTrue(thrown.getMessage().contains(resourceBundle.getString("argument")));
    }

    @Test
    void getAndSetTest() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("langModel_pl");
        SudokuField field = new SudokuField();
        SudokuFieldException thrown = assertThrows(SudokuFieldException.class,
                ()->field.setFieldValue(-3));
        Assert.assertTrue(thrown.getMessage().contains(resourceBundle.getString("argument")));
    }
}