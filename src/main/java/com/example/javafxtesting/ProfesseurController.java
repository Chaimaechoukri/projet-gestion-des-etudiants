package com.example.javafxtesting;
import dao.professor.SubjectDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import java.util.List;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.Student;

import java.sql.*;

public class ProfesseurController {

    @FXML
    private ComboBox<String> subjectComboBox;

    private SubjectDAO subjectDAO;

    // Méthode d'initialisation du contrôleur
    @FXML
    public void initialize() {
        subjectDAO = new SubjectDAO();

        int professorId = 2;

        // Appeler la méthode getSubjectsForProfessor pour récupérer les matières associées au professeur
        List<String> subjects = subjectDAO.getSubjectsForProfessor(professorId);

        // Pré-remplir le ComboBox avec les matières récupérées
        initializeComboBox(subjects);
    }

    // Méthode pour initialiser le ComboBox avec la liste des matières
    private void initializeComboBox(List<String> subjects) {
        subjectComboBox.getItems().addAll(subjects);
        subjectComboBox.setPromptText("Sélectionner une matière");
    }

    // Autres méthodes de gestion des événements, etc., si nécessaire




    @FXML
    private TableView<Student> studentTableView;

    private void listerEtudiants() {
        String className = "Nom_de_la_classe"; // Récupérer le nom de la classe sélectionnée
        String subject = subjectComboBox.getValue(); // Récupérer le nom de la matière sélectionnée

        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT s.student_id, s.first_name, s.last_name, c.class_name, su.subject_name, e.year " +
                    "FROM students s " +
                    "JOIN classes c ON s.class_id = c.class_id " +
                    "JOIN subjects su ON su.class_id = c.class_id " +
                    "JOIN education_levels e ON e.student_id = s.student_id " +
                    "WHERE c.class_name = ? AND su.subject_name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, className);
            statement.setString(2, subject);
            ResultSet resultSet = statement.executeQuery();

            studentTableView.getItems().clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String classNameResult = resultSet.getString("class_name");
                String subjectResult = resultSet.getString("subject_name");
                int year = resultSet.getInt("year");
                studentTableView.getItems().add(new Student(id, firstName, lastName, classNameResult, subjectResult, year));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
