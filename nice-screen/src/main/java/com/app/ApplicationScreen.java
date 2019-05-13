package com.app;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorAdjustBuilder;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ApplicationScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        HBox menuBar = new HBox(25);
        menuBar.setAlignment(Pos.CENTER);
        menuBar.setPadding(new Insets(50, 50, 50, 50));
        menuBar.setPrefHeight(150);
        menuBar.setAlignment(Pos.CENTER);
        menuBar.styleProperty().set("-fx-background-color: linear (0%,0%) to (100%,100%) stops (0.0,aqua) (1.0,red);");


        Image img1 = new Image(getClass().getResourceAsStream("/luna.png"));
        ImageView imageView1 = new ImageView(img1);
        menuBar.getChildren().addAll(imageView1);
        initImageNode(imageView1);


        Image img2 = new Image(getClass().getResourceAsStream("/apple_green.png"));
        ImageView imageView2 = new ImageView(img2);
        menuBar.getChildren().addAll(imageView2);
        initImageNode(imageView2);


        Image img3 = new Image(getClass().getResourceAsStream("/cofe.png"));
        ImageView imageView3 = new ImageView(img3);
        menuBar.getChildren().addAll(imageView3);
        initImageNode(imageView3);

        Image img4 = new Image(getClass().getResourceAsStream("/divan_cotton.png"));
        ImageView imageView4 = new ImageView(img4);
        menuBar.getChildren().addAll(imageView4);
        initImageNode(imageView4);


        BorderPane root = new BorderPane();
        root.setBottom(menuBar);


        StackPane view = new StackPane();
        view.getChildren().addAll(new Label("Text"));
        root.setCenter(view);

        Scene scene = new Scene(root, 720, 550, Color.LIGHTGRAY);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void initImageNode(ImageView node) {
        Reflection reflection = new Reflection();
        //reflection.setBottomOpacity(-6.0);
        node.setEffect(reflection);


        node.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), node);
                animationGrow.setToX(1.3);
                animationGrow.setToY(1.3);
                animationGrow.play();
            }
        });

        node.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), node);
                animationGrow.setToX(1);
                animationGrow.setToY(1);
                animationGrow.play();
                node.setEffect(reflection);
            }
        });

        node.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ColorAdjust effectPressed = new ColorAdjust();
                effectPressed.setBrightness(-0.5);
                node.setEffect(effectPressed);
            }
        });


    }

}
