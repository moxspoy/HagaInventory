package auth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
        primaryStage.setTitle(constant.Application.TITLE);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/img/logo.jpg")));
        primaryStage.setScene(new Scene(root, constant.Application.WIDTH, constant.Application.HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
