package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.data.IImportInitializer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.tinylog.Logger;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;


public class BatchImporterCarRideJSON extends importer<CarRide> {


    public Boolean importData(Path filePath, IImportInitializer initializer, boolean overwrite) {
        Logger.info("Importing");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        Function<JSONObject, List<CarRide>> importer = json -> {
            List<CarRide> carRideList = new LinkedList<>();
            JSONArray carRidesArray = json.getJSONArray("carrides");

            for (int i = 0; i < carRidesArray.length(); i++) {
                JSONObject carRideObject = carRidesArray.getJSONObject(i);
                JSONObject currencyObject = carRideObject.getJSONObject("currency");
                JSONObject categoryObject = carRideObject.getJSONObject("category");

                CarRide carRide;
                Currency currency;
                Category category;

                category = new Category(categoryObject.getString("guid"),
                        categoryObject.getString("name"),
                        categoryObject.getInt("color"));

                currency = new Currency(currencyObject.getString("guid"),
                        currencyObject.getString("name"),
                        currencyObject.getString("symbol"),
                        currencyObject.getDouble("rate_to_dollar"));

                carRide = new CarRide(carRideObject.getString("guid"),
                        carRideObject.getString("title"),
                        carRideObject.getString("description"),
                        carRideObject.getDouble("distance"),
                        carRideObject.getDouble("fuel_consumption"),
                        carRideObject.getDouble("cost_of_fuel_per_litre"),
                        carRideObject.getInt("passengers"),
                        carRideObject.getDouble("commission"),
                        category,
                        currency,
                        currency.getNewestRateToDollar(),
                        LocalDateTime.parse(carRideObject.getString("date"), formatter));
                carRideList.add(carRide);
            }
            return carRideList;
        };

        Function<List<CarRide>, Void> init = list -> {
            initializer.initializeCarRide(list, overwrite);
            return null;
        };

        return super.importData(filePath, importer, init);
    }
}
