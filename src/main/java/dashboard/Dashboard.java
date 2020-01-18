package dashboard;

import auth.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard {


    public void show() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
       Stage stage = new Stage();
       stage.setTitle("Dashboard | " + constant.Application.TITLE);
       stage.setScene(new Scene(root, constant.Application.WIDTH, constant.Application.HEIGHT));
       stage.getIcons().add(new Image(Main.class.getResourceAsStream("/img/logo.jpg")));
       stage.show();
   }
}
