package dao;

import model.Delibration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DelibrationDAO {
    private final String url = "jdbc:mysql://localhost:3306/test";
    private final String username = "root";
    private final String password = "";

    public void insertDelibration(int studentId, double note, int subjectId, String status) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO delibrations (note_id, student_id, subject_id, status_delibration) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDouble(1, note);
            statement.setInt(2, studentId);
            statement.setInt(3, subjectId);
            statement.setString(4, status);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
