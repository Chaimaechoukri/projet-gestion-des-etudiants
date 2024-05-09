package controller;

import com.example.javafxtesting.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import dao.UserDAO;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

    @FXML
    public void login() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();


        if (userDAO.authenticate(username, password)) {
            System.out.println("Login successful");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxtesting/professeur.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Login failed: incorrect username or password");
            showAlert("Error", "Incorrect username or password.");
        }
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void showRegisterStage(MouseEvent mouseEvent) {
    }
}
