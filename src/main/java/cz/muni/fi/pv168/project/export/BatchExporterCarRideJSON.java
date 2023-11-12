package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.CarRide;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BatchExporterCarRideJSON{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public void exportData(List<CarRide> carRides, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            JSONArray carRidesArray = new JSONArray();

            for (CarRide carRide : carRides) {
                JSONObject carRideObject = new JSONObject();
                JSONObject currencyObject = new JSONObject();
                JSONObject categoryObject = new JSONObject();

                // Populate categoryObject
                categoryObject.put("guid", carRide.getCategory().getGuid());
                categoryObject.put("name", carRide.getCategory().getName());
                categoryObject.put("color", carRide.getCategory().getColour());

                // Populate currencyObject
                currencyObject.put("name", carRide.getCurrency().getName());
                currencyObject.put("symbol", carRide.getCurrency().getSymbol());
                currencyObject.put("rate_to_dollar", carRide.getCurrency().getNewestRateToDollar());

                // Populate carRideObject
                carRideObject.put("guid", carRide.getGuid());
                carRideObject.put("title", carRide.getTitle());
                carRideObject.put("description", carRide.getDescription());
                carRideObject.put("distance", carRide.getDistance());
                carRideObject.put("fuel_consumption", carRide.getFuelConsumption());
                carRideObject.put("cost_of_fuel_per_litre", carRide.getCostOfFuelPerLitreInDollars());
                carRideObject.put("passengers", carRide.getNumberOfPassengers());
                carRideObject.put("commission", carRide.getCommission());
                carRideObject.put("date", carRide.getDate().format(formatter));
                carRideObject.put("category", categoryObject);
                carRideObject.put("currency", currencyObject);
                carRideObject.put("newest_conversion_rate", carRide.getConversionToDollars());

                carRidesArray.put(carRideObject);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("carrides", carRidesArray);

            fileWriter.write(jsonObject.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}