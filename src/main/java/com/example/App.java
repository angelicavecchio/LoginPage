package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

 try {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.setTitle("MANUTECH");
        stage.setResizable(true);
        stage.getIcons().add(new Image(getClass().getResource("/com/example/icon.png").toExternalForm()));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
    }

     static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setRoot(Parent root) {
    scene.setRoot(root);
}

    public static void main(String[] args) {
        launch();
    }

}