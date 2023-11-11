package cz.muni.fi.pv168.project.data;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;


public class TestDataGenerator {
    private final List<String> CATEGORIES = List.of(
            "BMW", "Tesla", "Skoda", "Subaru", "Honda", "Bentley", "Autobus", "Helicopter helicopter", "Páracopter páracopter", "Lamboghíni", "Motorka"
    );
    private final List<String> CARNAMES = List.of(
            "Karmen", "Ferda", "Luigiano", "Beatle", "S3XY", "Herbie", "Dedoles", "Scooby-van", "Debbie", "Trump mobile"
    );
    private final List<String> DESCRIPTIONS = List.of(
            "very bad car", "bad car", "pí car", "3.6 car", "good car", "very good car", "awesome car"
    );
    private final List<Tuple> CURRENCIES = List.of(
            new Tuple("USD", "US Dollar", 1.0),
            new Tuple("EUR", "Euro", 1.2),
            new Tuple("CZK", "Czech Koruna", 0.05)
    );
    private final Random randomGenerator = new Random();

    private Category createTestCategory(String name) {
        return new Category(UUID.randomUUID().toString(),
                name,
                randomGenerator.nextInt(-16581375, 16581375));
    }

    private CurrencyConversion createTestCurrencyConversion(Currency currency) {
        var curremcyConversion = new CurrencyConversion(currency, randomGenerator.nextDouble(0.1, 40), LocalDateTime.now(), currency.getConversions().size());
        currency.getConversions().add(curremcyConversion);
        return curremcyConversion;
    }

    public Category createBlankCategory() {
        return createTestCategory(CATEGORIES.get(randomGenerator.nextInt(CATEGORIES.size())));
    }

    private Template createTestTemplate(List<Category> categories, List<Currency> currencies) {
        String guid = UUID.randomUUID().toString();
        String name = CARNAMES.get(randomGenerator.nextInt(CARNAMES.size()));
        String description = DESCRIPTIONS.get(randomGenerator.nextInt(DESCRIPTIONS.size()));
        Double distance = randomGenerator.nextDouble(0, 1000);
        double fuelConsumption = randomGenerator.nextDouble(20, 40);
        int costOfFuelPerLitre = randomGenerator.nextInt(3, 15);
        int numberOfPassengers = randomGenerator.nextInt(1, 10);
        double commission = randomGenerator.nextInt(0, 100);
        Category category = categories.get(randomGenerator.nextInt(categories.size()));
        Currency currency = currencies.get(randomGenerator.nextInt(currencies.size()));
        return new Template(guid, name, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, category, currency, currency.getConversions().get(0));
    }

    private Currency createTestCurrency(Tuple curr) {
        var currency = new Currency(curr.name, curr.symbol, new LinkedList<>());
        currency.getConversions().add(createTestCurrencyConversion(currency));
        return currency;
    }

    public Template createBlankTemplate() {
        String guid = UUID.randomUUID().toString();
        String name = CARNAMES.get(randomGenerator.nextInt(CARNAMES.size()));
        String description = DESCRIPTIONS.get(randomGenerator.nextInt(DESCRIPTIONS.size()));
        Double distance = randomGenerator.nextDouble(0, 1000);
        double fuelConsumption = randomGenerator.nextDouble(20, 40);
        int costOfFuelPerLitre = randomGenerator.nextInt(3, 15);
        int numberOfPassengers = randomGenerator.nextInt(1, 10);
        double commission = randomGenerator.nextInt(0, 100);
        Category category = createTestCategory(CATEGORIES.get(randomGenerator.nextInt(CATEGORIES.size())));
        var currencies = createTestCurrencie(1);
        Currency currency = currencies.get(randomGenerator.nextInt(currencies.size()));
        return new Template(guid, name, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, category, currency, currency.getConversions().get(0));
    }

    private CarRide createTestRide(List<Category> categories, List<Currency> currencies) {
        String guid = UUID.randomUUID().toString();
        String name = CARNAMES.get(randomGenerator.nextInt(CARNAMES.size()));
        String description = DESCRIPTIONS.get(randomGenerator.nextInt(DESCRIPTIONS.size()));
        Double distance = randomGenerator.nextDouble(0, 1000);
        double fuelConsumption = randomGenerator.nextDouble(20, 40);
        int costOfFuelPerLitre = randomGenerator.nextInt(3, 15);
        int numberOfPassengers = randomGenerator.nextInt(1, 10);
        double commission = randomGenerator.nextInt(0, 100);
        LocalDateTime date = LocalDateTime.of(
                randomGenerator.nextInt(2023, 2024),
                randomGenerator.nextInt(10, 12),
                randomGenerator.nextInt(1, 29),
                randomGenerator.nextInt(0, 24),
                randomGenerator.nextInt(0, 60));
        Category category = categories.get(randomGenerator.nextInt(categories.size()));
        Currency currency = currencies.get(randomGenerator.nextInt(currencies.size()));
        return new CarRide(guid, name, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, date, category, currency, currency.getConversions().get(0));
    }

    public List<CarRide> createTestRides(int count, List<Category> categories, List<Currency> currencies) {
        return Stream
                .generate(() -> createTestRide(categories, currencies))
                .limit(count)
                .toList();
    }

    public List<Template> createTestTemplates(int count, List<Category> categories, List<Currency> currencies) {
        return Stream
                .generate(() -> createTestTemplate(categories, currencies))
                .limit(count)
                .toList();
    }

    public List<Category> createTestCategories(int count) {
        List<Category> categories = new ArrayList<>();

        for (String cat : CATEGORIES) {
            categories.add(createTestCategory(cat));
        }
        return categories.stream()
                .limit(count)
                .toList();
    }

    public List<Currency> createTestCurrencie(int count) {
        List<Currency> currencies = new ArrayList<>();

        for (Tuple curr : CURRENCIES) {
            currencies.add(createTestCurrency(curr));
        }
        return currencies.stream()
                .limit(count)
                .toList();
    }

    public record Tuple(String symbol, String name, Double conversionRate) {
    }
}
