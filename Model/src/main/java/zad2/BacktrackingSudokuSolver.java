package zad2;

import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver{
    public boolean solve(SudokuBoard sudokuBoard){
        List<List<SudokuField>> pom = sudokuBoard.getBoard();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (pom.get(row).get(col).getFieldValue() == 0) {
                    for (int number = 1; number <= 9; number++) {
                        if (sudokuBoard.checkBoard(row, col, number)) {
                            pom.get(row).get(col).setFieldValue(number);
                            if (solve(sudokuBoard)) {
                                return true;
                            } else {
                                pom.get(row).get(col).setFieldValue(0);
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
