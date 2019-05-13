package com.app.start;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.OutputStream;
import java.net.Socket;

public class ChatApplication extends Application {
    private ClientThread clientThread;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox hostBox = new VBox(10);
        Label hostLabel = new Label("Host");
        TextField hostTextField = new TextField();
        hostBox.getChildren().addAll(hostLabel, hostTextField);

        VBox portBox = new VBox(10);
        Label portLabel = new Label("Port");
        TextField portTextField = new TextField();
        portBox.getChildren().addAll(portLabel, portTextField);


        HBox commonBox = new HBox(10);
        commonBox.setAlignment(Pos.CENTER);
        commonBox.getChildren().addAll(hostBox, portBox);


        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Button connectButton = new Button("CONNECT");
        connectButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String host = hostTextField.getText();
                    String port = portTextField.getText();
                    Socket socket = new Socket(host, Integer.parseInt(port));


                    TextArea textArea = new TextArea();
                    clientThread = new ClientThread(socket, textArea);
                    clientThread.start();

                    TextField userInputField = new TextField();
                    Button userSendButton = new Button("SEND");
                    userSendButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            String text = userInputField.getText();
                            try {
                                OutputStream out = clientThread.getSocket().getOutputStream();
                                out.write(text.getBytes());
                                out.flush();
                                System.out.println("Message was sent");
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    });

                    HBox userActionPanel = new HBox(10);
                    userActionPanel.setAlignment(Pos.CENTER);
                    userActionPanel.getChildren().addAll(userInputField, userSendButton);

                    VBox userPanel = new VBox(10);
                    userPanel.setAlignment(Pos.CENTER);
                    userPanel.getChildren().addAll(textArea, userActionPanel);

                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().add(userPanel);
                    Scene scene = new Scene(stackPane, primaryStage.getWidth(), primaryStage.getHeight());
                    primaryStage.setScene(scene);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        vbox.getChildren().addAll(commonBox, connectButton);


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(vbox);

        Scene scene = new Scene(stackPane, 600, 600);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Chat");
        primaryStage.show();
    }
}
