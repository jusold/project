package example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public  class XMLImporter implements Importer {
    @Override
    public EBook importData(File filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filename);

            Element root = document.getDocumentElement();

            String title = getTextContent(root, "title");
            String author = getTextContent(root, "author");
            String content = getTextContent(root, "content");
            String styles = getTextContent(root, "styles");

            return new EBookBuilder()
                    .setTitle(title)
                    .setAuthor(author)
                    .setContent(content)
                    .setStyles(styles)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private String getTextContent(Element parent, String childName) {
        return parent.getElementsByTagName(childName).item(0).getTextContent();
    }
}


