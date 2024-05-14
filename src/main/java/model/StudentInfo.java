package model;

import java.util.ArrayList;
import java.util.List;

public class StudentInfo {

        private int studentId;
        private String username;
        private String password;
        private int roleId;
        private List<Grade> grades;
        private List<Deliberation> deliberations;

        public StudentInfo() {
            grades = new ArrayList<>();
            deliberations = new ArrayList<>();
        }

        // Constructor, getters, and setters
        public void addGrade(Grade grade) {
            grades.add(grade);
        }

        public void addDeliberation(Deliberation deliberation) {
            deliberations.add(deliberation);
        }
    }


