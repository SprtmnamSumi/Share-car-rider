package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * @author Sabrina Orálková, 525089
 */
public class BatchImporterCarRideJSON {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public List<CarRide> importData(String filePath) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath))); // TODO choose a different method

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray carRidesArray = jsonObject.getJSONArray("carrides");

            List<CarRide> carRideList = new LinkedList<>();

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

                currency = new Currency(categoryObject.getString("name"),
                        categoryObject.getString("symbol"),
                        categoryObject.getDouble("rate_to_dollar"));

                carRide = new CarRide(carRideObject.getString("guid"),
                        carRideObject.getString("title"),
                        carRideObject.getString("description"),
                        carRideObject.getDouble("distance"),
                        carRideObject.getDouble("fuel_consumption"),
                        carRideObject.getDouble("cost_of_fuel_per_litre"),
                        carRideObject.getInt("passengers"),
                        carRideObject.getDouble("commission"),
                        LocalDateTime.parse(carRideObject.getString("guid"), formatter),
                        category,
                        currency,
                        currency.getNewestRateToDollar());
                carRideList.add(carRide);
            }

            return carRideList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
