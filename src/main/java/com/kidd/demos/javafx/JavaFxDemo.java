package com.kidd.demos.javafx;/**
 * @author Kidd
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class JavaFxDemo extends Application {

    public static void main(String[] args) {
        JavaFxDemo.launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = JavaFxDemo.class.getResource("/javafx/javaFxDemo.fxml");
        Stage stage = new Stage();
        stage.setTitle("标题");
        stage.setScene(new Scene(FXMLLoader.load(url)));
        stage.show();
    }
}
