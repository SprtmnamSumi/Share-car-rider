package cz.muni.fi.pv168.project.export;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.data.IImportInitializer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;


public class BatchImporterCurrencyJSON extends importer<Currency> {
    public Boolean importData(Path filePath, IImportInitializer initializer, boolean overwrite) {
        Function<JSONObject, List<Currency>> importer = json -> {
            List<Currency> currencyList = new LinkedList<>();
            JSONArray currencyArray = json.getJSONArray("currencies");

            for (int i = 0; i < currencyArray.length(); i++) {
                JSONObject currencyObject = currencyArray.getJSONObject(i);

                Currency currency;

                currency = new Currency(currencyObject.getString("guid"),
                        currencyObject.getString("name"),
                        currencyObject.getString("symbol"),
                        currencyObject.getDouble("rate_to_dollar"));

                currencyList.add(currency);
            }
            return currencyList;
        };

        Function<List<Currency>, Void> init = list -> {
            initializer.initializeCurrency(list, overwrite);
            return null;
        };

        return super.importData(filePath, importer, init);
    }
}
    
    

