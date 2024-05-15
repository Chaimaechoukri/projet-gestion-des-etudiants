//package controller;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableView;
//import model.Student;
//
//import java.sql.*;
//import java.time.LocalDate;
//
//public class StudentController {
//
//    @FXML
//    private ComboBox<String> classNameTextField;
//
//    @FXML
//    private ComboBox<String> subjectComboBox;
//
//    @FXML
//    private TableView<Student> studentTableView;
//
//    public void listerEtudiants(String className, String subject) {
//        String url = "jdbc:mysql://localhost:3306/test";
//        String username = "root";
//        String password = "";
//        try (Connection conn = DriverManager.getConnection(url, username, password)) {
//            String query = "SELECT s.first_name, s.last_name, n.note, c.class_name " +
//                    "FROM students s " +
//                    "JOIN classes c ON s.class_id = c.class_id " +
//                    "JOIN notes n ON s.student_id = n.student_id " +
//                    "JOIN subjects su ON su.subject_id = n.subject_id " +
//                    "WHERE c.class_name = ? AND su.subject_name = ?";
//            PreparedStatement statement = conn.prepareStatement(query);
//            statement.setString(1, className);
//            statement.setString(2, subject);
//            ResultSet resultSet = statement.executeQuery();
//
//            studentTableView.getItems().clear();
//            while (resultSet.next()) {
//                String firstName = resultSet.getString("first_name");
//                String lastName = resultSet.getString("last_name");
//                double note = resultSet.getDouble("note");
//                String classNameResult = resultSet.getString("class_name");
//
//                // Créer une instance de Student avec les données récupérées
//                Student student = new Student();
//                student.setFirstName(firstName);
//                student.setLastName(lastName);
//                student.setNote(note);
//                student.setClassName(classNameResult);
//
//                // Ajouter l'étudiant à votre TableView
//                studentTableView.getItems().add(student);
//
//                System.out.println("Added to TableView: " + student);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
