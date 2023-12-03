package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class BatchImporterTemplateJSON {

    public List<Template> importData(Path filePath) {
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(filePath))) {
            byte[] buffer = new byte[4096];
            StringBuilder content = new StringBuilder();

            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }

            JSONObject jsonObject = new JSONObject(content.toString());
            JSONArray templateArray = jsonObject.getJSONArray("templates");

            List<Template> templateList = new LinkedList<>();

            for (int i = 0; i < templateArray.length(); i++) {
                JSONObject templateObject = templateArray.getJSONObject(i);
                JSONObject currencyObject = templateObject.getJSONObject("currency");
                JSONObject categoryObject = templateObject.getJSONObject("category");

                Template template;
                Currency currency;
                Category category;

                category = new Category(categoryObject.getString("guid"),
                        categoryObject.getString("name"),
                        categoryObject.getInt("color"));

                currency = new Currency(currencyObject.getString("guid"),
                        currencyObject.getString("name"),
                        currencyObject.getString("symbol"),
                        currencyObject.getDouble("rate_to_dollar"));

                template = new Template(templateObject.getString("guid"),
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
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
