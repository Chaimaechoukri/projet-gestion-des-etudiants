package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.DelibrationDAO;

import java.sql.SQLException;
import java.util.List;

public class DirpController {
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student, Integer> studentIdColumn;

    @FXML
    private TableColumn<Student, String> studentFirstNameColumn;

    @FXML
    private TableColumn<Student, String> studentLastNameColumn;

    @FXML
    private TableColumn<Student, String> studentClassColumn;

    @FXML
    private TableColumn<Student, Double> studentNoteColumn;

    @FXML
    private TableColumn<Student, String> delibrationColumn;

    @FXML
    private ComboBox<String> subjectComboBox;

    @FXML
    private Button confirmButton; // Bouton "Confirmer"

    private final StudentDAO studentDAO;
    private final SubjectDAO subjectDAO;
    private final DelibrationDAO delibrationDAO; // Ajout de la DAO pour les délibrations

    public DirpController() {
        this.studentDAO = new StudentDAO();
        this.subjectDAO = new SubjectDAO();
        this.delibrationDAO = new DelibrationDAO(); // Initialisation de la DAO pour les délibrations
    }

    @FXML
    private void initialize() {
        delibrationColumn.setCellValueFactory(new PropertyValueFactory<>("delibration"));
        // Configuration des colonnes de la TableView
        studentFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        studentLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        studentClassColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        studentNoteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        loadAllSubjects();
    }

    private void loadAllSubjects() {
        try {
            List<String> subjectNames = subjectDAO.getAllSubjectNames();
            subjectComboBox.getItems().addAll(subjectNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void listerEtudiants() {
        String subject = subjectComboBox.getValue();

        if (subject == null) {
            return;
        }

        studentTableView.getItems().clear();

        List<Student> students = studentDAO.getStudentsBySubject(subject);

        studentTableView.getItems().addAll(students);
    }

    @FXML
    private void confirmerDelibrations() {
        // Récupérer l'étudiant sélectionné dans le TableView
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            // Vérifier si les identifiants existent
            if (selectedStudent.getNoteId() != 0 && selectedStudent.getStudentId() != 0 && selectedStudent.getSubjectId() != 0) {
                // Enregistrer la délibération dans la base de données
                delibrationDAO.insertDelibration(selectedStudent.getStudentId(), selectedStudent.getNoteId(), selectedStudent.getSubjectId(), selectedStudent.getDelibration());
                System.out.println("Délibération enregistrée avec succès.");
            } else {
                System.out.println("Identifiants de note, d'étudiant ou de sujet manquants.");
            }
        } else {
            // Aucun étudiant sélectionné, afficher un message d'erreur ou gérer le cas selon vos besoins
            System.out.println("Aucun étudiant sélectionné.");
        }
    }

    }
