import dao.FileSudokuBoardDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import zad2.*;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class MenuController {

    private static SudokuBoard sudokuBoard = new SudokuBoard();
    public static int lang;

    private void gBoard() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuBoard.generateBoard();
        sudokuSolver.solve(sudokuBoard);
    }

    public static SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    @FXML
    public void easy() {
        gBoard();
        Random random = new Random();
        int pom[] = new int[3];
        int pom2[] = new int[3];
        for (int i = 0; i < 3; i++) {
            pom[i] = random.nextInt(9);
            pom2[i] = random.nextInt(9);
        }
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i == pom[k] && j == pom2[k]) {
                        sudokuBoard.set(i, j, 0);
                    }
                }
            }
        }
    }

    @FXML
    public void medium() {
        gBoard();
        Random random = new Random();
        int pom[] = new int[4];
        int pom2[] = new int[4];
        for (int i = 0; i < 4; i++) {
            pom[i] = random.nextInt(9);
            pom2[i] = random.nextInt(9);
        }
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i == pom[k] && j == pom2[k]) {
                        sudokuBoard.set(i, j, 0);
                    }
                }
            }
        }
    }

    @FXML
    public void hard() {
        gBoard();
        Random random = new Random();
        int pom[] = new int[5];
        int pom2[] = new int[5];
        for (int i = 0; i < 5; i++) {
            pom[i] = random.nextInt(9);
            pom2[i] = random.nextInt(9);
        }
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i == pom[k] && j == pom2[k]) {
                        sudokuBoard.set(i, j, 0);
                    }
                }
            }
        }
    }

    @FXML
    public void startGame(){
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/plansza.fxml"));
        if(lang==1){
            fxmlLoader.setResources(ResourceBundle.getBundle("bundles.language_eng"));
        }else{
            fxmlLoader.setResources(ResourceBundle.getBundle("bundles.language_pl"));
        }
        Pane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void wczytaj(){
        FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao("gra.bin");
        sudokuBoard = fileSudokuBoardDao.read();
    }

    @FXML
    public void eng(){
        lang=1;
    }

    @FXML
    public void pl(){
        lang=0;
    }
}
