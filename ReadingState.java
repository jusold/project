package example;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class ReadingState implements EBookState {
    @Override
    public void read(String title, String author, String content) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Read Book");
        dialog.setHeaderText("Read book");

        ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(close);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField titleTextArea = new TextField();
        titleTextArea.setEditable(false);
        titleTextArea.setText(title);

        TextField authorTextArea = new TextField();
        authorTextArea.setEditable(false);
        authorTextArea.setText(author);

        TextArea contentTextArea = new TextArea();
        contentTextArea.setEditable(false);
        contentTextArea.setText(content);

        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleTextArea, 1, 0);

        grid.add(new Label("Author:"), 0, 1);
        grid.add(authorTextArea, 1, 1);

        grid.add(new Label("Content:"), 0, 2);
        grid.add(contentTextArea, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();
    }

    @Override
    public void edit() {
        System.out.println("You can not editing");

    }

    @Override
    public void save() {
        System.out.println("saving");

    }
}






