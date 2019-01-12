package Pixelizer.NegaTiV444;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
        primaryStage.setTitle("Pixelizer");
        primaryStage.setScene(new Scene(root, 1400, 600));
        primaryStage.show();
        Controller.STAGE = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}