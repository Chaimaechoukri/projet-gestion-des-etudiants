package dao.professor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Note;
import model.Student;

import java.sql.*;

public class NoteDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    @FXML
    private TextField classNameTextField1;
    @FXML
    private ComboBox<String> subjectComboBox1;

    // Ajoutez ces champs pour stocker les valeurs de la classe et du sujet
    private String className;
    private String subjectName;

    // Ajoutez les méthodes setter pour modifier ces valeurs
    public void setClassName(String className) {
        this.className = className;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public ObservableList<Note> getAllNotes() {
        ObservableList<Note> notes = FXCollections.observableArrayList();

        // Utilisez className et subjectName ici dans votre requête SQL
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT subjects.subject_name AS subject_name, " +
                    "classes.class_name AS class_name, " +
                    "notes.note AS note, " +
                    "students.first_name AS first_name, " +
                    "students.last_name AS last_name, " +
                    "students.student_id AS student_id " + // Ajoutez cette colonne pour récupérer l'ID de l'étudiant
                    "FROM notes " +
                    "JOIN subjects ON notes.subject_id = subjects.subject_id " +
                    "JOIN classes ON notes.class_id = classes.class_id " +
                    "JOIN students ON notes.student_id = students.student_id " +
                    "WHERE classes.class_name = ? AND subjects.subject_name = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, className);
            statement.setString(2, subjectName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String subject = resultSet.getString("subject_name");
                String className = resultSet.getString("class_name");
                double note = resultSet.getDouble("note");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Integer associatedStudentId = resultSet.getInt("student_id"); // Supposons que le nom de la colonne dans la base de données est "associated_student_id"
                notes.add(new Note(subject, className, note, firstName, lastName, associatedStudentId));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }
}
