package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.Category;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Sabrina Orálková, 525089
 */
public class BatchExporterCategoryJSON {
    public void exportData(List<Category> categories, String filePath) {
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
            jsonObject.put("carrides", categoryArray);

            fileWriter.write(jsonObject.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
