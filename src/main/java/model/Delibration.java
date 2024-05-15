package model;

public class Delibration {
    public int getDelibrationId() {
        return delibrationId;
    }

    public void setDelibrationId(int delibrationId) {
        this.delibrationId = delibrationId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int delibrationId;
    private int noteId;
    private int studentId;
    private int subjectId;
    private String status;

    // Getters and Setters
}
