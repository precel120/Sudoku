package GUI;

import dao.FileSudokuBoardDao;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.apache.log4j.Logger;
import zad2.SudokuBoard;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PlanszaController {
    private SudokuBoard sudokuBoard;
    final static Logger logger = Logger.getLogger(PlanszaController.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang");

    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize() {
        sudokuBoard = MenuController.getSudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setText(Integer.toString(sudokuBoard.get(j, i)));
                if (textField.getText().equals("0")) {
                    textField.clear();
                    textField.setText(" ");
                }
                this.gridPane.add(textField, i, j);
            }
        }
    }

    @FXML
    public void zapisz() {
        FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao("gra.bin");
        fileSudokuBoardDao.write(sudokuBoard);
        //logger.info(bundle.getString("saved"));
    }

    @FXML
    public void sprawdz() {
        List<Node> childrens = gridPane.getChildren();
        int x, y, var;
        TextField value;
        String tmp;
        boolean sentry=false;

        for (Node node : childrens) {
            value = (TextField) node;
            x = gridPane.getRowIndex(node);
            y = gridPane.getColumnIndex(node);
            tmp = value.getText().trim();
            //if ((tmp.matches("[1-9]"))) var = Integer.parseInt(tmp);
             if (tmp.equals("") || tmp.equals(" ")) var = 0;
            else {
                 var = Integer.parseInt(tmp);
                //var = 0;
                //sentry=true;

            }
            sudokuBoard.set(x, y, var);
        }
        if(sentry) alertValue();
        if (sudokuBoard.checkBoard() && !findZero(sudokuBoard)) {
            alertPositive();
            logger.info(bundle.getString("gameOver"));
        } else {
            alertNegative();
            logger.info(bundle.getString("attempt"));
        }
    }

    private void alertPositive() {
        ResourceBundle bundle;
        if (Locale.getDefault().toString().equals("en")) bundle = ResourceBundle.getBundle("bundles.lang");
        else bundle = ResourceBundle.getBundle("bundles.lang_pl");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUDOKU INFO");
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("alertPositive"));
        alert.showAndWait();
    }

    private void alertNegative() {
        ResourceBundle bundle;
        if (Locale.getDefault().toString().equals("en")) bundle = ResourceBundle.getBundle("bundles.lang");
        else bundle = ResourceBundle.getBundle("bundles.lang_pl");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUDOKU INFO");
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("alertNegative"));
        alert.showAndWait();
    }

    private void alertValue() {
        ResourceBundle bundle;
        if (Locale.getDefault().toString().equals("en")) bundle = ResourceBundle.getBundle("bundles.lang");
        else bundle = ResourceBundle.getBundle("bundles.lang_pl");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUDOKU INFO");
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("alertValue"));
        alert.showAndWait();
    }

    private boolean findZero(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0) return true;
            }
        }
        return false;
    }

    @FXML
    public void exit(){
        logger.info(bundle.getString("gameFinished"));
        Platform.exit();
    }
}