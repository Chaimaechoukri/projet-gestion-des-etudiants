package dao;

import model.ClassInfo;
import model.Student;
import utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public List<Student> getAllStudents() throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getInt("class_id"),
                        rs.getString("photo_url"),
                        new ClassInfo(rs.getInt("class_id"), null, null)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}