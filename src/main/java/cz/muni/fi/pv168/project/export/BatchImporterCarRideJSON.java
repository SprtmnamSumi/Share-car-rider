package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class BatchImporterCarRideJSON {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public List<CarRide> importData(Path filePath) {
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(filePath))) {
            byte[] buffer = new byte[4096];
            StringBuilder content = new StringBuilder();

            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }

            JSONObject jsonObject = new JSONObject(content.toString());
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

                currency = new Currency(currencyObject.getString("symbol"),
                        currencyObject.getString("name"),
                        currencyObject.getDouble("rate_to_dollar"));

                carRide = new CarRide(carRideObject.getString("guid"),
                        carRideObject.getString("title"),
                        carRideObject.getString("description"),
                        carRideObject.getDouble("distance"),
                        carRideObject.getDouble("fuel_consumption"),
                        carRideObject.getDouble("cost_of_fuel_per_litre"),
                        carRideObject.getInt("passengers"),
                        carRideObject.getDouble("commission"),
                        LocalDateTime.parse(carRideObject.getString("date"), formatter),
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
