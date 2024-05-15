package com.example.javafxtesting;
import controller.NoteController;
import dao.professor.CustomTableCellFactory;
import dao.professor.NoteDAO;
import dao.professor.SubjectDAO;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import model.ClassInfo;
import model.Note;
import model.Student;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.input.MouseEvent;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.collections.ObservableList;

import javax.security.auth.Subject;
import java.util.Optional;
public class ProfesseurController {
    @FXML
    private TextField noteField;
    @FXML
    private TextField classField;
    @FXML
    private TableView<Note> tableView;
    @FXML
    private Label username;
    @FXML
    private ComboBox<String> subjectComboBox;
    @FXML
    private SubjectDAO subjectDAO;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane addNotesPane;
    @FXML
    private AnchorPane afficherNotsPane;
    @FXML
    private TableColumn<Note, String> firstNameCol;
    @FXML
    private TableColumn<Note, String> lastNameCol;
    @FXML
    private TableColumn<Note, String> classNameCol;
    @FXML
    private TableColumn<Note, String> subjectNameCol;
    @FXML
    private TableColumn<Note, Double> noteCol;
    @FXML
    private NoteDAO noteDAO = new NoteDAO();
    @FXML
    private Button loadDataButton;
    @FXML
    private ComboBox<String> subjectComboBox1;

    public TextField notetextfield;
    public TextField firstNametextField;
    public TextField lastNametextField;


    // Méthode d'initialisation du contrôleur



    @FXML
    public void initialize() {

////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////

        // Configurer les colonnes
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        classNameCol.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
        subjectNameCol.setCellValueFactory(cellData -> cellData.getValue().subjectNameProperty());
        noteCol.setCellValueFactory(cellData -> cellData.getValue().noteProperty().asObject());


        loadDataButton.setOnAction(event -> loadData());
        tableView.setOnMouseClicked(this::handleRowSelection);
        tableView.setOnMouseClicked(this::handleRowSelection1);

        handleTableViewSelection();
        // Créer une instance de NoteController
        NoteController noteController = new NoteController();

        // Attacher l'événement de sélection de ligne à la méthode noteSelect de l'instance de NoteController
        tableView.setOnMouseClicked(event -> noteController.noteSelect(tableView, firstNameField, lastNameField, classNameField, subjectField, noteField));

        configureColumns();


        // Masquer la page d'affichage des notes au démarrage
        afficherNotsPane.setVisible(false);

        // Associer les actions aux boutons
        addNotesButton.setOnAction(event -> showAddNotesPage());
        afficherNotsButton.setOnAction(event -> showAfficherNotsPage());

        // Ajoute un gestionnaire d'événements au bouton de déconnexion
        logoutButton.setOnAction(event -> handleLogout());

        subjectDAO = new SubjectDAO();

        int professorId = 2;

        // Appeler la méthode getSubjectsForProfessor pour récupérer les matières associées au professeur
        List<String> subjects = subjectDAO.getSubjectsForProfessor(professorId);

        // Pré-remplir le ComboBox avec les matières récupérées
        initializeComboBox(subjects);
        initializeComboBox1(subjects);
        handleTableViewSelection1();
        noteUpdate(new ActionEvent());

    }

    // Méthode pour initialiser le ComboBox avec la liste des matières
    private void initializeComboBox(List<String> subjects) {
        subjectComboBox.getItems().addAll(subjects);
        subjectComboBox.setPromptText("Sélectionner une matière");
    }
    // Ajout d'une méthode similaire pour initialiser subjectComboBox1
    private void initializeComboBox1(List<String> subjects) {
        subjectComboBox1.getItems().addAll(subjects);
        subjectComboBox1.setPromptText("Sélectionner une matière");
    }
    // Autres méthodes de gestion des événements, etc., si nécessaire

    @FXML
    private void handleLogout() {
        // Ferme la fenêtre principale
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();

        // Ouvre une nouvelle fenêtre de connexion
        openLoginWindow();
    }
    @FXML
    private void openLoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Connexion");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/// ///////////////////////////////////// lister les etudiant
    @FXML
    private TextField classNameTextField;
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student, Integer> studentId;
    @FXML
    private TableColumn<Student, String> firstName;
    @FXML
    private TableColumn<Student, String> lastName;
    @FXML
    private TableColumn<Student, LocalDate> dateOfBirth;
    @FXML
    private TableColumn<Student, Integer> classId;
    @FXML
    private TableColumn<Student, String> photoUrl;
    @FXML
    private TableColumn<Student, String> classInfo;
    @FXML
    public void listerEtudiants() {
        String className1 = classNameTextField.getText(); // Récupérer le nom de la classe à partir du champ de texte
        String subject = subjectComboBox.getValue(); // Récupérer le nom de la matière sélectionnée
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT s.student_id, s.first_name, s.last_name, s.date_of_birth, s.class_id, s.photo_url, c.class_name " +
                    "FROM students s " +
                    "JOIN classes c ON s.class_id = c.class_id " +
                    "JOIN subjects su ON su.class_id = c.class_id " +
                    "WHERE c.class_name = ? AND su.subject_name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, className1);
            statement.setString(2, subject);
            ResultSet resultSet = statement.executeQuery();

            studentTableView.getItems().clear();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate(); // Modifier selon le type dans votre base de données
                int classId = resultSet.getInt("class_id");
                String photoUrl = resultSet.getString("photo_url");
                String className = resultSet.getString("class_name");
                ClassInfo classInfo = new ClassInfo(classId, className, null); // Ajoutez les autres propriétés si nécessaires

                // Créer une instance de Student avec les données récupérées
                Student student = new Student(studentId, firstName, lastName, dateOfBirth, classId, photoUrl, classInfo);

                // Ajouter l'étudiant à votre TableView
                studentTableView.getItems().add(student);

                System.out.println("Added to TableView: " + student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private TableColumn<Student, String> noteCol1;
    private void configureColumns() {
        studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        classId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        photoUrl.setCellValueFactory(new PropertyValueFactory<>("photoUrl"));
        classInfo.setCellValueFactory(new PropertyValueFactory<>("classInfo"));


        // Configurez la colonne de notes pour qu'elle soit éditable
        noteCol1.setCellFactory(col -> CustomTableCellFactory.createNoteEditingCell());
        // Liez la colonne de notes à la propriété de note de la classe Student
        noteCol1.setCellValueFactory(new PropertyValueFactory<>("note"));
        // Assurez-vous que la colonne est éditable
        noteCol1.setEditable(true);
    }
    @FXML
    private Button addNotesButton;
    @FXML
    private Button afficherNotsButton;

    @FXML
    private void showAddNotesPage() {
        // Afficher la page d'ajout de notes
        addNotesPane.setVisible(true);

        // Masquer la page d'affichage des notes
        afficherNotsPane.setVisible(false);
    }
    @FXML
    private void showAfficherNotsPage() {
        // Afficher la page d'affichage des notes
        afficherNotsPane.setVisible(true);

        // Masquer la page d'ajout de notes
        addNotesPane.setVisible(false);
    }


////////////////////////////////////////// ajouter not
@FXML
private void handleTableViewSelection1() {
    System.out.println("Méthode handleTableViewSelection1() appelée !");
    studentSelect(tableView, firstNametextField, lastNametextField, classNameField, subjectField, notetextfield);
}
@FXML
public static void studentSelect(TableView<Note> studentTableView, TextField firstNametextField, TextField lastNametextField, TextField classNameField, TextField subjectField, TextField notetextfield) {
    System.out.println("Méthode noteSelect() appelée !");
    // Vérifier si une ligne est sélectionnée dans la TableView
    if (!studentTableView.getSelectionModel().isEmpty()) {
        // Rendre la colonne de la note éditable
        notetextfield.setEditable(true);
        notetextfield.setDisable(false);

        // Rendre les autres champs non éditables
        firstNametextField.setEditable(false);
        lastNametextField.setEditable(false);
        classNameField.setEditable(false);
        subjectField.setEditable(false);
        System.out.println("Méthode noteSelect() appelée !");

        // Récupérer la ligne sélectionnée
        Note selectedNote = studentTableView.getSelectionModel().getSelectedItem();
        if(selectedNote != null) {
            System.out.println("selectedNote n'est pas null");
        } else {
            System.out.println("selectedNote est null");
        }
        System.out.println("Méthode noteSelect() appelée !");

        // Récupérer les détails de la note sélectionnée
        String subjectName = selectedNote.getSubjectName();
        String className = selectedNote.getClassName();
        String firstName = selectedNote.getFirstName();
        String lastName = selectedNote.getLastName();
        double note = selectedNote.getNote();

        // Afficher la note dans la console pour déboguer
        System.out.println("Note récupérée : " + note);

        // Afficher les détails dans les champs de texte correspondants
        subjectField.setText(subjectName);
        classNameField.setText(className);
        firstNametextField.setText(firstName);
        lastNametextField.setText(lastName);
        notetextfield.setText(String.valueOf(note)); // Assurez-vous que la valeur de la note est définie dans le champ de texte notetextfield
    } else {
        // Si aucune ligne n'est sélectionnée, vider les champs de texte
        subjectField.clear();
        classNameField.clear();
        firstNametextField.clear();
        lastNametextField.clear();
        notetextfield.clear();
    }
}
@FXML
public void studentUpdate(ActionEvent actionEvent) {
        String subjectName = subjectField.getText();
        String className = classNameField.getText();
        String noteText = notetextfield.getText();
    String firstName = firstNametextField.getText();
    String lastName = lastNametextField.getText();

        if ( noteText.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() ) {
            try {
                double noteValue = Double.parseDouble(noteText);
                // Obtenir l'ID de l'étudiant à partir de la base de données en utilisant le prénom et le nom de famille
                Integer studentId = getStudentIdFromDatabase(firstName, lastName);
                if (studentId != null) {
                    updateStudent(new Note(subjectName, className, noteValue, firstName, lastName, studentId), studentId);
                } else {
                    showAlert("Error", "Student not found in the database!", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException e) {
                // Gérer le cas où le texte de la note ne peut pas être converti en double
                showAlert("Error", "Invalid note value. Please enter a valid number.", Alert.AlertType.ERROR);
            }
        }
    }
@FXML
public void updateStudent(Note note, Integer studentId) {
        String query = "UPDATE notes SET note = ? WHERE student_id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setDouble(1, note.getNote());
                pstmt.setInt(2, studentId);
                pstmt.executeUpdate();
                showAlert("Success", "Note updated successfully!", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Update Error", "Failed to update note!", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Connection Error", "Failed to connect to the database!", Alert.AlertType.ERROR);
        }
    }
@FXML
private void handleRowSelection1(MouseEvent event) {
        if (event.getClickCount() == 1) { // Vérifie si l'événement est un simple clic
            studentSelect(tableView, firstNametextField, lastNametextField, classNameField, subjectField, notetextfield);
        }
    }


///////////////////////////////////////////////////////////
    // /////////////////////// pour afficher notes xml


    @FXML
    private TextField classNameTextField1; // Ajout du champ FXML pour le TextField
    @FXML
    public void loadData() {
        // Récupérer les valeurs nécessaires depuis les champs de texte et de combobox
        String className = classNameTextField1.getText();
        String subjectName = subjectComboBox1.getValue();

        // Créer une instance de NoteDAO
        NoteDAO noteDAO = new NoteDAO();

        // Appeler les méthodes de configuration pour définir les valeurs
        noteDAO.setClassName(className);
        noteDAO.setSubjectName(subjectName);

        // Récupérer la liste des notes en fonction des valeurs définies
        ObservableList<Note> notes = noteDAO.getAllNotes();

        // Utiliser la liste de notes récupérée comme nécessaire
        // Par exemple, vous pouvez l'assigner à votre TableView
        tableView.setItems(notes);

        // table_View1.setItems(noteDAO.getAllNotes());

    }








    ///////////// update Nots

    @FXML
    private TextField subjectField;
    @FXML
    private TextField classNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;


@FXML
    private void handleTableViewSelection() {
        NoteController noteController = new NoteController();
        noteController.noteSelect(tableView, firstNameField, lastNameField, classNameField, subjectField, noteField);
    }

    @FXML

    public void updateNote(Note note, Integer studentId) {
        String query = "UPDATE notes SET note = ? WHERE student_id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setDouble(1, note.getNote());
                pstmt.setInt(2, studentId);
                pstmt.executeUpdate();
                showAlert("Success", "Note updated successfully!", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Update Error", "Failed to update note!", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Connection Error", "Failed to connect to the database!", Alert.AlertType.ERROR);
        }
    }

    @FXML

    public void noteUpdate(ActionEvent actionEvent) {
        String subjectName = subjectField.getText();
        String className = classNameField.getText();
        String noteText = noteField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        if (!subjectName.isEmpty() && !className.isEmpty() && !noteText.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty()) {
            try {
                double noteValue = Double.parseDouble(noteText);
                // Obtenir l'ID de l'étudiant à partir de la base de données en utilisant le prénom et le nom de famille
                Integer studentId = getStudentIdFromDatabase(firstName, lastName);
                if (studentId != null) {
                    updateNote(new Note(subjectName, className, noteValue, firstName, lastName, studentId), studentId);
                } else {
                    showAlert("Error", "Student not found in the database!", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException e) {
                // Gérer le cas où le texte de la note ne peut pas être converti en double
                showAlert("Error", "Invalid note value. Please enter a valid number.", Alert.AlertType.ERROR);
            }
        }
    }

    private Integer getStudentIdFromDatabase(String firstName, String lastName) {
        Integer studentId = null;
        String query = "SELECT student_id FROM students WHERE first_name = ? AND last_name = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    studentId = resultSet.getInt("student_id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Database Error", "Failed to execute query!", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Connection Error", "Failed to connect to the database!", Alert.AlertType.ERROR);
        }
        return studentId;
    }

  // Méthode pour mettre à jour les détails de la note avec les nouvelles valeurs de l'interface utilisateur
    @FXML
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleRowSelection(MouseEvent event) {
        if (event.getClickCount() == 1) { // Vérifie si l'événement est un simple clic

            NoteController noteController = new NoteController();
            // Appeler la méthode noteSelect() lorsque la ligne est sélectionnée
            NoteController.noteSelect(tableView, firstNameField, lastNameField, classNameField, subjectField, noteField);
        }
    }


}
