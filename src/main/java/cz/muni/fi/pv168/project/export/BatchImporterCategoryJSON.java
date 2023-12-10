package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.data.IImportInitializer;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import org.json.JSONArray;
import org.json.JSONObject;


public class BatchImporterCategoryJSON extends importer<Category> {


    public Boolean importData(Path filePath, IImportInitializer initializer, boolean overwrite) {
        Function<JSONObject, List<Category>> importer = json -> {
            List<Category> categoryList = new LinkedList<>();
            JSONArray categoryArray = json.getJSONArray("categories");

            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject categoryObject = categoryArray.getJSONObject(i);

                Category category;

                category = new Category(categoryObject.getString("guid"),
                        categoryObject.getString("name"),
                        categoryObject.getInt("color"));

                categoryList.add(category);
            }
            return categoryList;
        };

        Function<List<Category>, Void> init = list -> {
            initializer.initializeCategory(list, overwrite);
            return null;
        };

        return super.importData(filePath, importer, init);
    }
}
