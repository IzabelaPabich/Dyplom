package view;

import controllers.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainWindow.fxml"));
        Parent root = fxmlLoader.load();
        MainWindowController mainWindowController = fxmlLoader.getController();
        mainWindowController.init(stage);

        Scene scene = new Scene(root);
        stage.setTitle("Praca dymplomowa");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
