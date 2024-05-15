package controller;

import com.example.javafxtesting.MainApp;
import com.example.javafxtesting.ProfesseurController;
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
        if (username.isEmpty() || password.isEmpty()) {  // Fixed this line
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            if (userDAO.authenticate(username, password)) {
                int role_id = userDAO.getRoleByUsername(username);
                String xml = switch (role_id) {
                    case 1 -> "StudentView.fxml";
                    case 2 -> "AdminView.fxml";
                    case 3 -> "professeur.fxml";
                    case 4 -> "DirectorView.fxml";
                    default -> "DefaultView.fxml";
                };
                System.out.println("Login successful");
                System.out.println(xml);
                FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(xml));
                Parent root = loader.load();
                ProfesseurController professeurController = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Login failed: incorrect username or password");
                showAlert("Error", "Incorrect username or password.");
            }
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