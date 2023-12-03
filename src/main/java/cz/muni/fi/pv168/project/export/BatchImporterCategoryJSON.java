package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.Category;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;


public class BatchImporterCategoryJSON {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public List<Category> importData(Path filePath) {
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(filePath))) {
            byte[] buffer = new byte[4096];
            StringBuilder content = new StringBuilder();

            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }

            JSONObject jsonObject = new JSONObject(content.toString());
            JSONArray categoryArray = jsonObject.getJSONArray("categories");

            List<Category> categoryList = new LinkedList<>();

            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject categoryObject = categoryArray.getJSONObject(i);

                Category category;

                category = new Category(categoryObject.getString("guid"),
                        categoryObject.getString("name"),
                        categoryObject.getInt("color"));

                categoryList.add(category);
            }

            return categoryList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
