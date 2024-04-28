package model;


public class ClassInfo {
    private Integer classId;
    private String className;
    private String classLevel;

    public ClassInfo(Integer classId, String className, String classLevel) {
        this.classId = classId;
        this.className = className;
        this.classLevel = classLevel;
    }

    // Getters and setters
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }
}
