package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.Template;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class BatchExporterTemplateJSON {
    public void exportData(List<Template> templates, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            JSONArray templateArray = new JSONArray();

            for (Template template : templates) {
                JSONObject templateObject = new JSONObject();
                JSONObject currencyObject = new JSONObject();
                JSONObject categoryObject = new JSONObject();

                // Populate categoryObject
                categoryObject.put("guid", template.getCategory().getGuid());
                categoryObject.put("name", template.getCategory().getName());
                categoryObject.put("color", template.getCategory().getColour());

                // Populate currencyObject
                currencyObject.put("guid", template.getCategory().getGuid());
                currencyObject.put("name", template.getCurrency().getName());
                currencyObject.put("symbol", template.getCurrency().getSymbol());
                currencyObject.put("rate_to_dollar", template.getCurrency().getNewestRateToDollar());

                // Populate carRideObject
                templateObject.put("guid", template.getGuid());
                templateObject.put("title", template.getTitle());
                templateObject.put("description", template.getDescription());
                templateObject.put("distance", template.getDistance());
                templateObject.put("fuel_consumption", template.getFuelConsumption());
                templateObject.put("cost_of_fuel_per_litre", template.getCostOfFuelPerLitreInDollars());
                templateObject.put("passengers", template.getNumberOfPassengers());
                templateObject.put("commission", template.getCommission());
                templateObject.put("category", categoryObject);
                templateObject.put("currency", currencyObject);
                templateObject.put("newest_conversion_rate", template.getConversionToDollars());

                templateArray.put(templateObject);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("templates", templateArray);

            fileWriter.write(jsonObject.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
