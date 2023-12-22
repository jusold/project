package example;

import example.EBook;
import example.EBookState;
import javafx.scene.control.Alert;

public class SavingState implements EBookState {
    @Override
    public void read(String title, String author, String content) {
        System.out.println("You need reading");
    }

    @Override
    public void edit() {
        System.out.println("You need editing");

    }
    private String editedBookData;
    @Override
    public void save() {
        if (editedBookData == null) {
            showAlert("Cancel Operation", "No changes were made to the book. The operation was canceled.");
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
