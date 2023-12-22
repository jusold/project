package example;

import java.io.File;

public class main {
    public static void main(String[] args) {
        Importer jsonImporter = new JSONImporter();
        EBook jsonEBook = jsonImporter.importData(new File("data_array.json"));
        System.out.println(jsonEBook.getAuthor());

//        Importer xmlImporter = new XMLImporter();
//        EBook xmlEBook = xmlImporter.importData("data.xml");
//        System.out.println(xmlEBook.getTitle());
    }
}
