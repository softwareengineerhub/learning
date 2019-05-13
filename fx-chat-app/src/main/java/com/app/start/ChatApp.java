package com.app.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChatApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");


        Button button = new Button();
        button.setText("Button");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(button);

        Scene scene = new Scene(stackPane, 400, 600);

        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
