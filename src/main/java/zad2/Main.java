package zad2;

public class Main {
    public static void main(String[] args) {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.generateBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        int tmp = sudokuBoard.get(0,0);
        SudokuRow sudokuRow1 = sudokuBoard.getRow(1);
        SudokuRow sudokuRow = sudokuBoard.getRow(0);

        System.out.println(sudokuRow.toString());



    }

}
