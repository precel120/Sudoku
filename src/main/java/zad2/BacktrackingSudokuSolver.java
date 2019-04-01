package zad2;

public class BacktrackingSudokuSolver implements SudokuSolver{
    public boolean solve(SudokuBoard sudokuBoard){
        SudokuField[][] pom = sudokuBoard.getBoard();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (pom[row][col].getFieldValue() == 0) {
                    for (int number = 1; number <= 9; number++) {
                        if (sudokuBoard.checkBoard(row, col, number)) {
                            pom[row][col].setFieldValue(number);
                            if (solve(sudokuBoard)) {
                                return true;
                            } else {
                                pom[row][col].setFieldValue(0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
