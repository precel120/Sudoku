import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import zad2.SudokuBoard;

public class PlanszaController {

    @FXML
    private GridPane gridPane;
    @FXML
    private void initialize(){
        SudokuBoard sudokuBoard = MenuController.getSudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setText(Integer.toString(sudokuBoard.get(i,j)));
                this.gridPane.add(textField,i,j);
            }
        }
    }
}