package controller;

import dao.StudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;

import java.sql.SQLException;

public class StudentController {
    public TableColumn studentIdColumn;
    public TableColumn dateOfBirthColumn;
    public TableColumn classNameColumn;
    public TableColumn photoUrlColumn;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;

    private final StudentDAO studentDao;

    public StudentController() {
        studentDao = new StudentDAO();
    }

    @FXML
    private void initialize() throws SQLException {
        //firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        loadData();
    }

    private void loadData() throws SQLException {
        ObservableList<Student> studentList = FXCollections.observableArrayList(studentDao.getAllStudents());
        studentTable.setItems(studentList);
    }
}

