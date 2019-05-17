import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import zad2.*;

import java.io.IOException;
import java.util.Random;

public class MenuController {

    private SudokuBoard sudokuBoard = new SudokuBoard();
    private MainController  mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void gBoard() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuBoard.generateBoard();
        sudokuSolver.solve(sudokuBoard);
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
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("plansza.fxml"));
        Pane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainController.setScreen(pane);
    }
}
