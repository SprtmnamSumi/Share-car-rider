package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.data.ImportInitializer;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import org.json.JSONArray;
import org.json.JSONObject;


public class BatchImporterTemplateJSON extends importer<Template> {

    public List<Template> importData(Path filePath, ImportInitializer initializer, boolean overwrite) {
        Function<JSONObject, List<Template>> importer = json -> {
            List<Template> templateList = new LinkedList<>();
            JSONArray templateArray = json.getJSONArray("templates");

            for (int i = 0; i < templateArray.length(); i++) {
                JSONObject templateObject = templateArray.getJSONObject(i);
                JSONObject currencyObject = templateObject.getJSONObject("currency");
                JSONObject categoryObject = templateObject.getJSONObject("category");

                var category = new Category(categoryObject.getString("guid"),
                        categoryObject.getString("name"),
                        categoryObject.getInt("color"));

                var currency = new Currency(currencyObject.getString("guid"),
                        currencyObject.getString("name"),
                        currencyObject.getString("symbol"),
                        currencyObject.getDouble("rate_to_dollar"));

                var template = new Template(templateObject.getString("guid"),
                        templateObject.getString("title"),
                        templateObject.getString("description"),
                        templateObject.getDouble("distance"),
                        templateObject.getDouble("fuel_consumption"),
                        templateObject.getDouble("cost_of_fuel_per_litre"),
                        templateObject.getInt("passengers"),
                        templateObject.getDouble("commission"),
                        category,
                        currency,
                        currency.getNewestRateToDollar());
                templateList.add(template);
            }
            return templateList;
        };

        Function<List<Template>, Void> init = list -> {
            initializer.initializeTemplate(list, overwrite);
            return null;
        };

        return super.importData(filePath, importer, init);
    }
}

