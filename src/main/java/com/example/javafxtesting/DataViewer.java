package com.example.javafxtesting;

import dao.professor.NoteDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Note;

public class DataViewer extends Application {

    private NoteDAO noteDAO = new NoteDAO();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("professeur.fxml"));
        Parent root = loader.load();

        // Obtenir une référence au contrôleur du fichier FXML
        ProfesseurController controller = loader.getController();

        // Charger les données dans le TableView via le contrôleur
        controller.loadData();

        // Afficher la scène
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
