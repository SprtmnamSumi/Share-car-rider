package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.Category;
import org.json.JSONArray;
import org.json.JSONObject;
import org.tinylog.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class BatchExporterCategoryJSON {
    public boolean exportData(List<Category> categories, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            JSONArray categoryArray = new JSONArray();

            for (Category category : categories) {
                JSONObject categoryObject = new JSONObject();

                // Populate categoryObject
                categoryObject.put("guid", category.getGuid());
                categoryObject.put("name", category.getName());
                categoryObject.put("color", category.getColour());

                categoryArray.put(categoryObject);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("categories", categoryArray);

            fileWriter.write(jsonObject.toString(2));
        } catch (IOException e) {
            Logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
