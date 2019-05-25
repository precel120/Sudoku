import dao.FileSudokuBoardDao;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import zad2.SudokuBoard;

public class PlanszaController {
    private SudokuBoard sudokuBoard;
    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize(){
        sudokuBoard = MenuController.getSudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setText(Integer.toString(sudokuBoard.get(i,j)));
                if(textField.getText().equals("0")){
                    textField.clear();
                    textField.setText("");
                }
                this.gridPane.add(textField,i,j);
            }
        }
    }

    @FXML
    public void zapisz(){
        FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao("gra.bin");
        fileSudokuBoardDao.write(sudokuBoard);
    }

    @FXML
    public boolean sprawdz(){
        TextField[][] textFields = new TextField[9][9];
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.generateBoard();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = getCell(i, j, gridPane);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (textFields[i][j].getText().equals("")) {
                    textFields[i][j].setText("0");
                }
                sudokuBoard.set(i, j, Integer.parseInt(textFields[i][j].getText()));
            }
        }

        if (sudokuBoard.checkBoard()) {
            System.out.println("Wygrales");
            return true;
        }else {
            System.out.println("Przegrales");
            return false;
        }
    }

    private TextField getCell(int col, int row, final GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return (TextField) node;
                }
            }
        }
        return null;
    }
}