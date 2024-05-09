package dao.professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SubjectDAO {


        public int getProfessorIdByUsername(String username) {
            int professorId = -1; // Valeur par défaut si l'ID n'est pas trouvé

            // Établir une connexion à la base de données
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
                // Écrire la requête SQL pour récupérer l'ID du professeur par son nom d'utilisateur
                String sql = "SELECT professor_id FROM professors WHERE username = ?";
                // Préparer la requête SQL avec le paramètre pour le nom d'utilisateur
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);

                    // Exécuter la requête SQL
                    try (ResultSet resultSet = statement.executeQuery()) {
                        // Traiter les résultats de la requête
                        if (resultSet.next()) {
                            professorId = resultSet.getInt("professor_id");
                        } else {
                            System.out.println("Professor not found for username: " + username);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer les exceptions SQL
            }

            return professorId;
        }



    public List<String> getSubjectsForProfessor(int professorId) {
        List<String> subjectNames = new ArrayList<>();

        // Établir une connexion à la base de données
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
            // Écrire la requête SQL pour récupérer les noms des matières associées au professeur
            String sql = "SELECT s.subject_name " +
                    "FROM subjects s " +
                    "JOIN professor_subject ps ON s.subject_id = ps.subject_id " +
                    "WHERE ps.professor_id = ?";

            // Préparer la requête SQL avec le paramètre pour professor_id
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, professorId);

                // Exécuter la requête SQL
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Traiter les résultats de la requête
                    while (resultSet.next()) {
                        String subjectName = resultSet.getString("subject_name");
                        subjectNames.add(subjectName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions SQL
        }

        return subjectNames;
    }
}
