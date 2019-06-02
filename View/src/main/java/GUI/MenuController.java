package GUI;

import dao.FileSudokuBoardDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import zad2.BacktrackingSudokuSolver;
import zad2.SudokuBoard;
import zad2.SudokuSolver;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;


public class MenuController  {

    private static SudokuBoard sudokuBoard = new SudokuBoard();

    private void gBoard() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuBoard.generateBoard();
        sudokuSolver.solve(sudokuBoard);
    }

    public static SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    final static Logger logger = Logger.getLogger(MenuController.class);

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
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang");
        logger.info(bundle.getString("startEasy"));
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
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang");
        logger.info(bundle.getString("startMedium"));
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
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang");
        logger.info(bundle.getString("startHard"));
    }

    @FXML
    public void startGame() {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/plansza.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang");
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.lang"));
        Pane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            logger.error(bundle.getString("ioe"), e);
            e.printStackTrace();
        }
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        logger.info(bundle.getString("started"));
    }

    @FXML
    public void wczytaj(){
        FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao("gra.bin");
        sudokuBoard = fileSudokuBoardDao.read();
    }

    @FXML
    public void authors() {
        ResourceBundle bundle = ResourceBundle.getBundle("resources.LRB");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("title"));
        alert.setHeaderText(bundle.getString("authors"));
        alert.showAndWait();
    }

    @FXML
    public void eng(){
        Locale.setDefault(new Locale("en"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang");
        logger.info(bundle.getString("languageChanged"));
        loadMenu();
    }

    @FXML
    public void pl(){
        Locale.setDefault(new Locale("pl"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang");
        logger.info(bundle.getString("languageChanged"));
        loadMenu();
    }

    public void loadMenu(){
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang");
        Pane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            logger.fatal(bundle.getString("ioe"), e);
            e.printStackTrace();
        }
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        logger.debug(bundle.getString("menuLoaded"));

    }
}
