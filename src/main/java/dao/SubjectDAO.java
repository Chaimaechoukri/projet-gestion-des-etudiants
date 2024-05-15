package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    private final String url = "jdbc:mysql://localhost:3306/test";
    private final String username = "root";
    private final String password = "";

    public List<String> getAllSubjectNames() throws SQLException {
        List<String> subjectNames = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT subject_name FROM subjects";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    subjectNames.add(rs.getString("subject_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectNames;
    }
}
