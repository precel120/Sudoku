package exceptions;

public class SudokuException extends IllegalArgumentException {
    public SudokuException(String message){
        super(message);
    }
}
