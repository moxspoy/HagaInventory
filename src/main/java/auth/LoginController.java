package auth;

import dashboard.Dashboard;
import database.ConnectionFactory;
import helper.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Properties;

public class LoginController {
    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox roleChoiceBox;

    @FXML
    private Button loginButton;

    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {
        Window owner = loginButton.getScene().getWindow();

        if (userNameField.getText().isEmpty() || userNameField.getText().length() < 5) {
            showAlert(Alert.AlertType.ERROR, owner, "Username salah!",
                    "Silahkan masukkan username anda, panjang username minimal 5 karakter");
            return;
        }
        if (passwordField.getText().isEmpty() || passwordField.getText().length() < 5) {
            showAlert(Alert.AlertType.ERROR, owner, "Password kosong!",
                    "Silahkan masukkan password anda, panjang password minimal 5 karakter");
            return;
        }

        String email = userNameField.getText();
        String password = passwordField.getText();
        String encryptedPassword = encryptPassword(password);
        String role = roleChoiceBox.getValue().toString();

        ConnectionFactory db = new ConnectionFactory();
        boolean flag = db.login(email, encryptedPassword, role);
        User currentUser = db.getCurrentUser();

        if (!flag) {
            infoBox("Username atau password salah, silahkan coba lagi", null, "Failed");
        } else {
            Helper.saveCurrentUser(currentUser);
            openDashboardWindow(owner);
        }
    }

    private void openDashboardWindow(Window window) throws IOException, SQLException {
        Dashboard dashboard = new Dashboard();
        dashboard.show();
        closeLoginWindow(window);
    }

    private void closeLoginWindow(Window window) {
        Stage stage = (Stage) window;
        stage.hide();
    }

    public static String encryptPassword(String input){
        String encPass=null;
        if(input == null) return null;

        try{
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(),0,input.length());
            encPass = new BigInteger(1,digest.digest()).toString(16);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return encPass;
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
