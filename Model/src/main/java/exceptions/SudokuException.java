package exceptions;

import zad2.SudokuBoard;

public class SudokuException extends IllegalArgumentException {
    public SudokuException(String message){
        super(message);
    }
}
