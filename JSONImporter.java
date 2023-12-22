package example;

import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONImporter implements Importer {
    @Override
    public EBook importData(File filename) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(filename.toURI())));
                JSONObject jsonObject = new JSONObject(content);


                return new EBookBuilder()
                        .setTitle(jsonObject.getString("title"))
                        .setAuthor(jsonObject.getString("author"))
                        .setContent(jsonObject.getString("content"))
                        .setStyles(jsonObject.getString("styles"))
                        .build();
            } catch (Exception e) {
                e.printStackTrace();

            }
        return null;
        }

    }







