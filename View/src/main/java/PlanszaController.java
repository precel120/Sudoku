import dao.FileSudokuBoardDao;
import javafx.application.Platform;
import javafx.fxml.FXML;
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
                textField.setText(Integer.toString(sudokuBoard.get(j,i)));
                if(textField.getText().equals("0")){
                    textField.clear();
                    textField.setText(" ");
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
    public void sprawdz(){
        System.out.print("sprawdzone");
    }
}