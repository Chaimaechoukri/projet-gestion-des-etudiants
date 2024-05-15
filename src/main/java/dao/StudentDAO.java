package dao;

import model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final String url = "jdbc:mysql://localhost:3306/test";
    private final String username = "root";
    private final String password = "";

    public List<Student> getStudentsBySubject(String subjectName) {
        List<Student> students = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT s.student_id, s.first_name, s.last_name, n.note, c.class_name, " +
                    "CASE " +
                    "    WHEN n.note > 10 THEN 'Validé' " +
                    "    WHEN n.note >= 7 AND n.note <= 10 THEN 'Rattrapage' " +
                    "    WHEN n.note < 7 THEN 'Non validé' " +
                    "END AS deliberation " +
                    "FROM students s " +
                    "JOIN classes c ON s.class_id = c.class_id " +
                    "JOIN notes n ON s.student_id = n.student_id " +
                    "JOIN subjects su ON su.subject_id = n.subject_id " +
                    "WHERE su.subject_name = ?";






            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, subjectName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                double note = resultSet.getDouble("note");
                String className = resultSet.getString("class_name");
                String deliberation = resultSet.getString("deliberation");

                Student student = new Student(firstName, lastName, note, className);
                student.setDelibration(deliberation); // Mettez à jour la délibération de l'étudiant
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}
