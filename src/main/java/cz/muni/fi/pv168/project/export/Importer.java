package cz.muni.fi.pv168.project.export;

import org.json.JSONObject;
import org.tinylog.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public abstract class Importer<T> {

    public Boolean importData(Path filePath, Function<JSONObject, List<T>> importer, Function<List<T>, Void> initialize) {
        List<T> templateList;
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(filePath))) {
            byte[] buffer = new byte[4096];
            StringBuilder content = new StringBuilder();

            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }

            JSONObject jsonObject = new JSONObject(content.toString());
            templateList = importer.apply(jsonObject);
        } catch (IOException e) {
            Logger.error(e.getMessage());
            return false;
        }
        initialize.apply(templateList);
        return true;
    }
}
