package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.Currency;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;


public class BatchImporterCurrencyJSON {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public List<Currency> importData(Path filePath) {
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(filePath))) {
            byte[] buffer = new byte[4096];
            StringBuilder content = new StringBuilder();

            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }

            JSONObject jsonObject = new JSONObject(content.toString());
            JSONArray currencyArray = jsonObject.getJSONArray("currencies");

            List<Currency> currencyList = new LinkedList<>();

            for (int i = 0; i < currencyArray.length(); i++) {
                JSONObject currencyObject = currencyArray.getJSONObject(i);

                Currency currency;

                currency = new Currency(currencyObject.getString("name"),
                        currencyObject.getString("symbol"),
                        currencyObject.getDouble("rate_to_dollar"));

                currencyList.add(currency);
            }

            return currencyList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
