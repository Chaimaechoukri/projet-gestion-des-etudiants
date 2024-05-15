package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Note;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.Note;

public class NoteController {

    // Méthode pour afficher les détails de la note sélectionnée

    public static void noteSelect(TableView<Note> tableView, TextField firstNameField, TextField lastNameField, TextField classNameField, TextField subjectField, TextField noteField) {
        System.out.println("Méthode noteSelect() appelée !");
        // Vérifier si une ligne est sélectionnée dans la TableView
        if (!tableView.getSelectionModel().isEmpty()) {
            // Rendre la colonne de la note éditable
            noteField.setEditable(true);
            noteField.setDisable(false);

            // Rendre les autres champs non éditables
            firstNameField.setEditable(false);
            lastNameField.setEditable(false);
            classNameField.setEditable(false);
            subjectField.setEditable(false);
            // Récupérer la ligne sélectionnée
            Note selectedNote = tableView.getSelectionModel().getSelectedItem();

            // Récupérer les détails de la note sélectionnée
            String subjectName = selectedNote.getSubjectName();
            String className = selectedNote.getClassName();
            String firstName = selectedNote.getFirstName();
            String lastName = selectedNote.getLastName();
            double note = selectedNote.getNote();

            // Afficher les détails dans les champs de texte correspondants
            subjectField.setText(subjectName);
            classNameField.setText(className);
            firstNameField.setText(firstName);
            lastNameField.setText(lastName);
            noteField.setText(String.valueOf(note));
        } else {
            // Si aucune ligne n'est sélectionnée, vider les champs de texte
            subjectField.clear();
            classNameField.clear();
            firstNameField.clear();
            lastNameField.clear();
            noteField.clear();
        }
    }


}
