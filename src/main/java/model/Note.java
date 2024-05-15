package model;

import javafx.beans.property.*;

public class Note {
    private final StringProperty subjectName;
    private final StringProperty className;
    private final DoubleProperty note;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private static Integer associatedStudentId;


    public Note(String subjectName, String className, double note, String firstName, String lastName,int associatedStudentId ) {
        this.subjectName = new SimpleStringProperty(subjectName);
        this.className = new SimpleStringProperty(className);
        this.note = new SimpleDoubleProperty(note);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.associatedStudentId = associatedStudentId;

    }

    public String getSubjectName() {
        return subjectName.get();
    }

    public StringProperty subjectNameProperty() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName.set(subjectName);
    }

    public String getClassName() {
        return className.get();
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public double getNote() {
        return note.get();
    }

    public DoubleProperty noteProperty() {
        return note;
    }

    public void setNote(double note) {
        this.note.setValue(note);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public static Integer getAssociatedStudentId() {
        return associatedStudentId;
    }

}

