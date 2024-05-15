package model;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private double note;
    private String className;
    private String delibration; // Renommé de "deliberation" à "delibration"
    private int noteId; // Ajout de l'identifiant de la note
    private int subjectId; // Ajout de l'identifiant du sujet

    public Student(String firstName, String lastName, double note, String className) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.note = note;
        this.className = className;
        this.delibration = determineDelibration(note);
    }

    private String determineDelibration(double note) {
        if (note > 10) {
            return "Validé";
        } else if (note >= 7 ) {
            return "Rattrapage";
        } else {
            return "Non validé";
        }
    }

    public String getDelibration() {
        return delibration;
    }

    public void setDelibration(String delibration) {
        this.delibration = delibration;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    // Getters and Setters for other fields
}
