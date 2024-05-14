package Student;

import com.example.javafxtesting.MainApp;
import dao.EtudDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileStudent implements Initializable {

    @FXML
    private TableColumn<?, ?> Année;

    @FXML
    private TableColumn<?, ?> Modules;

    @FXML
    private TableColumn<?, ?> Notes;

    @FXML
    private AnchorPane NotesStudent;

    @FXML
    private Button NotesStudentButton;

    @FXML
    private Button Profils;

    @FXML
    private Button ProfilsStudentBoutton;

    @FXML
    private TableColumn<?, ?> Semester;

    @FXML
    private Button Sortie;

    @FXML
    private Label Welcome;

    @FXML
    private ComboBox<?> choisir;

    @FXML
    private AnchorPane profile_form;
    @FXML
    private Label classLabel;
    @FXML
    private Label dobLabel;
    @FXML
    private Label nameLabel;


    @FXML
    void NotesStudentOnActionP(ActionEvent event) throws IOException {

        if (event.getSource() == NotesStudentButton) {
            Stage stage1 = (Stage) NotesStudentButton.getScene().getWindow();
            stage1.close();
            System.out.println("button clicket profile");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("notes.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println(" ");
        }
    }

    @FXML
    void ProfilsStudentOnAction(ActionEvent event) throws IOException {

        if (event.getSource() == ProfilsStudentBoutton) {
            Stage stage2 = (Stage) ProfilsStudentBoutton.getScene().getWindow();
            stage2.close();
            System.out.println("button clicket profile");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("profilsStudent.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println(" ");
        }
    }

    @FXML
    void SeconnecteOnAction(ActionEvent event) throws IOException {

        if (event.getSource() == Sortie) {
            Stage stage2 = (Stage) Sortie.getScene().getWindow();
            stage2.close();
            System.out.println("button clicket profile");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("");
        }
    }


    // Suppose you have an array or list of students
    private Student[] students;

    // This method will be called after the FXML file has been loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Initialisation de EtudiantDAO
            EtudDAO etudDAO = new EtudDAO();

            // Obtention des informations de l'étudiant par son ID (vous devez fournir l'ID ici)
            int studentId = 3;
            Student student = etudDAO.getStudentById(studentId);
            // Affichage des informations de l'étudiant
            if (student != null) {
                nameLabel.setText("Nom: " + ((Student) student).getFirstName() + " " + student.getLastName());
                dobLabel.setText("Date de Naissance: " + student.getDateOfBirth().toString());
                classLabel.setText("Classe: " + student.getClassId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs lors de la récupération des données de l'étudiant
        }
    }
}


    /*int studentId;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Initialisation de EtudiantDAO
            EtudDAO etudDAO = new EtudDAO();
            // Obtention des informations de l'étudiant par son ID (vous devez fournir l'ID ici)
            Student student = etudDAO.getStudentById(studentId);
            // Affichage des informations de l'étudiant
            if (student != null) {
                nameLabel.setText("Nom: " + ((Student) student).getFirstName() + " " + student.getLastName());
                dobLabel.setText("Date de Naissance: " + student.getDateOfBirth().toString());
                classLabel.setText("Classe: " + student.getClassId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs lors de la récupération des données de l'étudiant
        }
    }
}
/*   @Override
    public void initialize(URL location, ResourceBundle resources) {
        Student student = EtudDAO.getStudentById(studentId);
        if (student != null) {
            nameLabel.setText("Nom: " + student.getFirstName() + " " + student.getLastName());
            dobLabel.setText("Date de Naissance: " + student.getDateOfBirth().toString());
            classLabel.setText("Classe: " + student.getClassId());
        } else {
            System.out.println("Étudiant non trouvé pour l'ID: " + studentId);
        }
    }
}
  */
    /*public void initialize(int studentId) {
        Student student = EtudDAO.getStudentById(studentId);
        if (student != null) {
            nameLabel.setText("Nom: " + student.getFirstName() + " " + student.getLastName());
            dobLabel.setText("Date de Naissance: " + student.getDateOfBirth().toString());
            classLabel.setText("Classe: " + student.getClassId());
        } else {
            System.out.println("Étudiant non trouvé pour l'ID: " + studentId);
        }
    }
}
*/


    /*  try {
            // Initialisation de StudentDAO
            EtudDAO studentDAO = new EtudDAO();
            // Obtention des informations de l'étudiant par son nom d'utilisateur (vous devez fournir le nom d'utilisateur ici)
           // Remplacez "nom_utilisateur" par le nom d'utilisateur réel
            int student_id = Integer.parseInt("");
            Student student = studentDAO.getStudentById(student_id);
            // Affichage des informations de l'étudiant
            if (student != null) {
                nameLabel.setText("Nom: " + student.getFirstName() + " " + student.getLastName());
                dobLabel.setText("Date de Naissance: " + student.getDateOfBirth().toString());
                classLabel.setText("Classe: " + student.getClassId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs lors de la récupération des données de l'étudiant
        }
    }
   /* public void initialize(URL location, ResourceBundle resources) {
            try {
                // Initialisation de EtudiantDAO
                EtudDAO etudDAO = new EtudDAO();

                // Obtention des informations de l'étudiant par son ID (vous devez fournir l'ID ici)
                int studentId = 3;
                Student student = etudDAO.getStudentById(studentId);
                // Affichage des informations de l'étudiant
                if (student != null) {
                    nameLabel.setText("Nom: " + ((Student) student).getFirstName() + " " + student.getLastName());
                    dobLabel.setText("Date de Naissance: " + student.getDateOfBirth().toString());
                    classLabel.setText("Classe: " + student.getClassId());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Gestion des erreurs lors de la récupération des données de l'étudiant
            }
        }

    */
