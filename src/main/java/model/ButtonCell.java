//package model;
//
//import javafx.scene.control.TableCell;
//import javafx.scene.control.Button;
//import javafx.geometry.Pos;
//import javafx.scene.layout.HBox;
//import dao.DelibrationDAO;
//
//import java.sql.SQLException;
//
//public class ButtonCell<S, T> extends TableCell<S, T> {
//
//    private final DelibrationDAO delibrationDAO;
//
//    public ButtonCell() {
//        this.delibrationDAO = new DelibrationDAO();
//    }
//
//    @Override
//    protected void updateItem(T item, boolean empty) {
//        super.updateItem(item, empty);
//        if (!empty) {
//            HBox hb = new HBox(7);
//            hb.setAlignment(Pos.CENTER);
//
//            Button valideButton = new Button("Valide");
//            valideButton.setOnAction(event -> affecterDelibration("Valide"));
//
//            Button nonValideButton = new Button("Non Valide");
//            nonValideButton.setOnAction(event -> affecterDelibration("Non Valide"));
//
//            Button rattrapageButton = new Button("Rattrapage");
//            rattrapageButton.setOnAction(event -> affecterDelibration("Rattrapage"));
//
//            hb.getChildren().addAll(valideButton, nonValideButton, rattrapageButton);
//            setGraphic(hb);
//        } else {
//            setGraphic(null);
//        }
//    }
//
//    private void affecterDelibration(String valueDelibration) {
//        T item = getItem();
//        if (item instanceof Student) {
//            Student student = (Student) item;
//            int studentId = student.getId();
//            // Remplacer "getSubjectId()" et "getNoteId()" par les méthodes appropriées de votre modèle Student
//            int subjectId = student.getSubjectId();
//            int noteId = student.getNoteId();
//            try {
//                // Ici, vous devez appeler la méthode de votre DAO pour insérer les données dans la table Delibration
//                delibrationDAO.insertDelibration(studentId, subjectId, noteId, valueDelibration);
//            } catch (SQLException e) {
//                e.printStackTrace(); // Gérez les exceptions de manière appropriée
//            }
//        }
//    }
//}
