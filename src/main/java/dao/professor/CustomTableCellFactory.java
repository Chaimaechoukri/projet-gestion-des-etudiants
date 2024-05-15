package dao.professor;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import model.Student;

public class CustomTableCellFactory {

    public static TableCell<Student, String> createNoteEditingCell() {
        return new TableCell<Student, String>() {
            private final TextField textField = new TextField();

            {
                textField.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (event.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                });
            }

            @Override
            public void startEdit() {
                super.startEdit();
                if (isEmpty()) {
                    return;
                }
                textField.setText(getString());
                setText(null);
                setGraphic(textField);
                textField.requestFocus();
                textField.selectAll();
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                setText(getString());
                setGraphic(null);
            }

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item);
                    setGraphic(null);
                }
            }

            private String getString() {
                return getItem() == null ? "" : getItem();
            }
        };
    }
}
