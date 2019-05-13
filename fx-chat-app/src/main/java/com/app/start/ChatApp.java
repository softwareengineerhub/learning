package com.app.start;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

public class ChatApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");
        TextField textField = new TextField();
        Label label = new Label("Label");
        Button button = new Button();
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //label.setText("Hello from EventHandler");
                String text = textField.getText();
                label.setText(text);
            }
        });

        button.setText("Button");




        /*StackPane stackPane = new StackPane();
        stackPane.getChildren().add(button);
        stackPane.getChildren().add(label);
        Scene scene = new Scene(stackPane, 400, 600);*/

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(button, label, textField);
        Scene scene = new Scene(vBox, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
