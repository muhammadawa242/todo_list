package com.example.demo4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class app extends Application {
    static final int WIDTH = 1360;
    static final int HEIGHT = 705;

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("javafx.version"));
        FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("view1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),WIDTH,app.HEIGHT);

        stage.setTitle("ToDo List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}