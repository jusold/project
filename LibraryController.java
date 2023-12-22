package com.example.demo;

import example.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class LibraryController implements Initializable {

    @FXML
    private ListView<String> booksList;

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private TextField styles;

    @FXML
    private TextArea content;

    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        booksList.setFixedCellSize(50.0);
    }

    @FXML
    protected void searchBook() throws IOException {

        String search_text = search.getText().strip().toLowerCase();
        loadBooks();
        if (search_text.length() >= 3) {
            ArrayList<String> results = new ArrayList<>();

            for (String book : booksList.getItems()) {
                if (book.toLowerCase().contains(search_text)) results.add(book);
            }

            booksList.getItems().clear();
            if (results.size() > 0) {
                for (String found_book : results) booksList.getItems().add(found_book);
                booksList.refresh();
            }
        }
    }

    @FXML
    protected void editBook() throws IOException {
        ObservableList<Integer> selectedIndices = booksList.getSelectionModel().getSelectedIndices();

        EditingState eBookState = new EditingState();
        eBookState.edit();

        if (!booksList.getItems().isEmpty() && selectedIndices.size() == 1) {
            String bookToEdit = booksList.getItems().get(selectedIndices.get(0));
            String oldIsbn = bookToEdit.split("\n")[0];

            String str = ((EditingState) eBookState).getResult();
            if (str != null) {

                Path p = Paths.get("src/main/data/" + oldIsbn + ".txt");
                Files.write(p, Collections.emptyList(), StandardCharsets.UTF_8);


                Files.write(p, str.getBytes(), StandardOpenOption.CREATE);


                eBookState.save();
                loadBooks();
                search.setText("");
            } else {
                System.out.println("Invalid:");
            }
        } else {
            System.out.println("Invalid selection or editing was canceled.");
        }
    }


    @FXML
    protected void deleteBook() throws IOException {
        ObservableList<Integer> selectedIndices = booksList.getSelectionModel().getSelectedIndices();

        if (selectedIndices.size() == 1) {
            String bookToEdit = booksList.getItems().get(selectedIndices.get(0));
            String oldIsbn = bookToEdit.split("\n")[0];
            Path p = Paths.get("src/main/data/" + oldIsbn + ".txt");
            File fileToDelete = new File(p.toString());
            fileToDelete.delete();

            loadBooks();
            search.setText("");
        }
    }

    @FXML
    protected void addItem() throws IOException {
        String author_text = author.getText();
        String title_name = title.getText();
        String content_text = content.getText();
        String styles_text = styles.getText();

        StringBuilder sb = new StringBuilder();
        sb.append(author_text);
        sb.append("\n");
        sb.append(title_name);
        sb.append("\n");
        sb.append(content_text);
        sb.append("\n");
        sb.append(styles_text);

        String data = new String(sb);
        Path p = Paths.get("src/main/data/" + title_name + ".txt");
        File myObj = new File(String.valueOf(p));
        if (myObj.createNewFile()) {
            FileWriter myWriter = new FileWriter(String.valueOf(p));
            myWriter.write(data);
            myWriter.close();
        }

        author.setText("");
        title.setText("");
        content.setText("");
        styles.setText("");

        this.loadBooks();
    }

    public static ArrayList<String> listFilesForFolder(final File folder) throws IOException {
        ArrayList<String> al = new ArrayList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {

            List<String> lines = Files.readAllLines(Paths.get(fileEntry.getPath()));


            if (lines.size() > 1) {

                String read = lines.get(1);
                al.add(read.strip());
            } else if (!lines.isEmpty()) {

                System.out.println("Warning: File " + fileEntry.getName() + " has only one line.");

            }
        }
        return al;

    }

    public void loadBooks() throws IOException {
        Path p = Paths.get("src/main/data");
        final File folder = new File(String.valueOf(p));
        ArrayList<String> al = listFilesForFolder(folder);
        booksList.getItems().clear();
        for (String book : al) {
            booksList.getItems().add(book);
        }
        booksList.refresh();
    }

    @FXML
    private void readBook() throws IOException {
        ObservableList<Integer> selectedIndices = booksList.getSelectionModel().getSelectedIndices();

        if (!booksList.getItems().isEmpty() && selectedIndices.size() == 1) {
            String bookToRead = booksList.getItems().get(selectedIndices.get(0));
            String oldIsbn = bookToRead.split("\n")[0];

            Path filePath = Paths.get("src/main/data/" + oldIsbn + ".txt");

            try {
                List<String> bookInfo = Files.readAllLines(filePath);

                if (bookInfo.size() >= 3) {
                    String title = bookInfo.get(0);
                    String author = bookInfo.get(1);
                    String content = bookInfo.get(2);

                    EBookState eBookState = new ReadingState();
                    eBookState.read(title, author, content);
                } else {
                    System.out.println("Invalid file format");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid selection");
        }
    }

    @FXML
    private void importData() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select XML or JSON File");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            EBook eBook = null;

            String extension = getFileExtension(file.getName());

            if ("xml".equalsIgnoreCase(extension)) {
                eBook = new XMLImporter().importData(file);
            } else if ("json".equalsIgnoreCase(extension)) {
                eBook = new JSONImporter().importData(file);
            }

            if (eBook != null) {
                author.setText(eBook.getAuthor());
                title.setText(eBook.getTitle());
                content.setText(eBook.getContent());
                styles.setText(eBook.getStyles());
                showAlert("Import Successful", "Title: " + eBook.getTitle());
                this.loadBooks();
                search.setText("");
            } else {
                showAlert("Import Failed", "Failed to import data.");
            }
        }
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return ""; // Расширение отсутствует
        }
        return fileName.substring(dotIndex + 1);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void changeScene() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // scene

        Stage stage = HelloApplication.getPrimaryStage();
        stage.hide();
        stage.setTitle("EBOOK");
        stage.setScene(scene);
        stage.show();
    }

}