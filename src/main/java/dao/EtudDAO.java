package dao;
import java.sql.*;

import model.ClassInfo;
import model.Student;
import utils.DatabaseUtil;

public class EtudDAO {

    private Connection connection;

    // Constructeur
    public EtudDAO() throws SQLException {
        this.connection = DatabaseUtil.getConnection();
    }
    public static Student getStudentById(int student_id) {
        Student student = null;

        // Établir une connexion à la base de données
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
            // Écrire la requête SQL pour récupérer toutes les informations de l'étudiant par son nom d'utilisateur
            String sql = "SELECT * FROM students WHERE student_id = ?";
            // Préparer la requête SQL avec le paramètre pour le nom d'utilisateur
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, student_id);
                // Exécuter la requête SQL
                try (ResultSet rs = statement.executeQuery()) {
                    // Traiter les résultats de la requête
                    if (rs.next()) {
                        student = new Student(
                                rs.getInt("student_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getDate("date_of_birth").toLocalDate(),
                                rs.getInt("class_id"),
                                rs.getString("photo_url"),
                                new ClassInfo(rs.getInt("class_id"), null, null)
                        );
                    } else {
                        System.out.println("Student not found for username: " + student_id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions SQL
        }

        return student;
    }



    // Méthode pour récupérer un étudiant par son ID
    /*public Student getStudentById(int studentId) throws SQLException {
        Student student = null;

        String query = "SELECT * FROM students WHERE student_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = new Student(
                            rs.getInt("student_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("date_of_birth").toLocalDate(),
                            rs.getInt("class_id"),
                            rs.getString("photo_url"),
                            new ClassInfo(rs.getInt("class_id"), null, null)
                    );
                    // Vous pouvez ajouter le reste des champs ici si nécessaire
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-lancer si vous souhaitez que le gestionnaire d'appels le gère
        }
        return student;
    }

     */
}
