package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Gui extends Application {
    private Stage stagePom;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stagePom = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
        Locale.setDefault(new Locale("en"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.lang"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        stagePom.setScene(scene);
        stagePom.show();
    }
}
