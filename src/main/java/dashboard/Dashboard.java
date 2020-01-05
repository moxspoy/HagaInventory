package dashboard;

import database.product.ProductDatabase;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard {


    public void show() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
       Stage stage = new Stage();
       stage.setTitle("Dashboard | " + constant.Application.TITLE);
       stage.setScene(new Scene(root, constant.Application.WIDTH, constant.Application.HEIGHT));
       stage.show();
   }
}
