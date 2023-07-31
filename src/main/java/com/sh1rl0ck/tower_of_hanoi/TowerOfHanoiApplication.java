package com.sh1rl0ck.tower_of_hanoi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TowerOfHanoiApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TowerOfHanoiApplication.class.getResource("tower-of-hanoi-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 600);
        stage.setTitle("Tower of Hanoi");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(TowerOfHanoiApplication.class.getResourceAsStream("logo.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}