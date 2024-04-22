package tn.star.pi5star;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public abstract class mainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("/ajouterformation.fxml"));
        try {
            Parent fxmlLoader=fxml.load();
            Scene scene=new Scene(fxmlLoader);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}