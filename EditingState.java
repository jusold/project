package example;

import example.EBook;
import example.EBookState;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class EditingState implements EBookState {
    @Override
    public void read(String title, String author, String content) {
        System.out.println("You can not read while you edit book");



    }
    private String editedBookData;
    private String result;
    public String getResult() {
        return this.editedBookData;
    }
    @Override
    public void edit() {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Edit Book");
            dialog.setHeaderText("Edit EBook");

            ButtonType confirm = new ButtonType("Save");
            dialog.getDialogPane().getButtonTypes().addAll(confirm, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField author = new TextField();
            author.setPromptText("Author");
            TextField title = new TextField();
            title.setPromptText("Title");
            TextArea content = new TextArea();
            content.setPromptText("Content");
            TextField styles = new TextField();
            styles.setPromptText("Style");

            grid.add(new Label("Author:"), 0, 0);
            grid.add(author, 1, 0);
            grid.add(new Label("Title:"), 0, 1);
            grid.add(title, 1, 1);
            grid.add(new Label("Content:"), 0, 2);
            grid.add(content, 1, 2);
            grid.add(new Label("Style:"), 0, 3);
            grid.add(styles, 1, 3);
            dialog.getDialogPane().setContent(grid);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == confirm) { return author.getText() + "\n"+ title.getText() + "\n"+ content.getText()+ "\n" + styles.getText();
                }
                return null;
            });
            Optional<String> rslt = dialog.showAndWait();
            if (rslt.isPresent() ) { this.editedBookData = rslt.get();
            }
            else this.editedBookData = null;
    }

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





