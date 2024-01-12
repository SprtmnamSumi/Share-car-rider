package cz.muni.fi.pv168.project.data;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Template;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


@SuppressWarnings("SpellCheckingInspection")
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
    private final List<Tuple> CURRENCIES;
    private final Random randomGenerator = new Random();
    private final GuidProvider guidProvider;

    public TestDataGenerator(GuidProvider guidProvider) {
        this.guidProvider = guidProvider;

        CURRENCIES = List.of(
                new Tuple(guidProvider.newGuid(), "USD", "US Dollar", 1.0),
                new Tuple(guidProvider.newGuid(), "EUR", "Euro", 0.85),
                new Tuple(guidProvider.newGuid(), "CZK", "Czech Koruna", 21.0),
                new Tuple(guidProvider.newGuid(), "GBP", "British Pound", 0.72),
                new Tuple(guidProvider.newGuid(), "JPY", "Japanese Yen", 109.0),
                new Tuple(guidProvider.newGuid(), "CHF", "Swiss Franc", 0.92),
                new Tuple(guidProvider.newGuid(), "CAD", "Canadian Dollar", 1.26),
                new Tuple(guidProvider.newGuid(), "AUD", "Australian Dollar", 1.29),
                new Tuple(guidProvider.newGuid(), "NZD", "New Zealand Dollar", 1.35),
                new Tuple(guidProvider.newGuid(), "RUB", "Russian Ruble", 73.0),
                new Tuple(guidProvider.newGuid(), "INR", "Indian Rupee", 73.0),
                new Tuple(guidProvider.newGuid(), "BRL", "Brazilian Real", 5.0),
                new Tuple(guidProvider.newGuid(), "CNY", "Chinese Yuan", 6.5),
                new Tuple(guidProvider.newGuid(), "MXN", "Mexican Peso", 20.0),
                new Tuple(guidProvider.newGuid(), "SGD", "Singapore Dollar", 1.35),
                new Tuple(guidProvider.newGuid(), "HKD", "Hong Kong Dollar", 7.8),
                new Tuple(guidProvider.newGuid(), "NOK", "Norwegian Krone", 8.5)
        );
    }

    private Category createTestCategory(String name) {
        return new Category(guidProvider.newGuid(),
                name,
                randomGenerator.nextInt(-16581375, 16581375));
    }

    private Template createTestTemplate(List<Category> categories, List<Currency> currencies) {
        String guid = guidProvider.newGuid();
        String name = CARNAMES.get(randomGenerator.nextInt(CARNAMES.size()));
        String description = DESCRIPTIONS.get(randomGenerator.nextInt(DESCRIPTIONS.size()));
        Double distance = randomGenerator.nextDouble(0, 1000);
        double fuelConsumption = randomGenerator.nextDouble(20, 40);
        int costOfFuelPerLitre = randomGenerator.nextInt(3, 15);
        int numberOfPassengers = randomGenerator.nextInt(1, 10);
        double commission = randomGenerator.nextInt(0, 100);
        Category category = categories.get(randomGenerator.nextInt(categories.size()));
        Currency currency = currencies.get(randomGenerator.nextInt(currencies.size()));
        return new Template(guid, name, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, category, currency, currency.getNewestRateToDollar());
    }

    private Currency createTestCurrency(Tuple curr) {
        return new Currency(curr.guid, curr.name, curr.symbol, curr.conversionRate);
    }

    private CarRide createTestRide(List<Category> categories, List<Currency> currencies) {
        String guid = guidProvider.newGuid();
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
        return new CarRide(guid, name, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, category, currency, currency.getNewestRateToDollar(), date);
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

    public record Tuple(String guid, String symbol, String name, Double conversionRate) {
    }
}
