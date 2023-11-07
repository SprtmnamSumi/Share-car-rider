package cz.muni.fi.pv168.project.data;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public final class TestDataGenerator {

    private final Random randomGenerator = new Random();
    private static final List<String> CATEGORIES = List.of(
            "BMW", "Tesla", "Skoda", "Subaru", "Honda", "Bentley", "Autobus", "Helicopter helicopter", "Páracopter páracopter"
    );
    private static final List<String> CARNAMES = List.of(
            "Karmen", "Ferda", "Luigiano", "Beatle", "S3XY"
    );
    private static final List<String> COLORS = List.of(
            "white", "black", "red", "blue", "green", "yellomello", "grey"
    );

    private static final List<String> DESCRIPTIONS = List.of(
           "very bad car", "bad car", "3.6 car", "good car", "very good car", "awesome car"
    );

    public Category createTestCategory() {
        return new Category(UUID.randomUUID().toString(),
                CATEGORIES.get(randomGenerator.nextInt(CATEGORIES.size())),
                COLORS.get(randomGenerator.nextInt(COLORS.size())));
    }

    public Template createTestTemplate() {
        String guid = UUID.randomUUID().toString();
        String name = CARNAMES.get(randomGenerator.nextInt(CARNAMES.size()));
        String description = DESCRIPTIONS.get(randomGenerator.nextInt(DESCRIPTIONS.size()));
        Double distance = randomGenerator.nextDouble(0, 1000);
        double fuelConsumption = randomGenerator.nextDouble(20, 40);
        int costOfFuelPerLitre = randomGenerator.nextInt(3, 15);
        int numberOfPassengers = randomGenerator.nextInt(1, 10);
        double commission = randomGenerator.nextInt(0, 100);
        Category category = createTestCategory();
        return new Template(guid, name, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, category);
    }

    public CarRide createTestRide() {
        String guid = UUID.randomUUID().toString();
        String name = CARNAMES.get(randomGenerator.nextInt(CARNAMES.size()));
        String description = DESCRIPTIONS.get(randomGenerator.nextInt(DESCRIPTIONS.size()));
        Double distance = randomGenerator.nextDouble(0, 1000);
        double fuelConsumption = randomGenerator.nextDouble(20, 40);
        int costOfFuelPerLitre = randomGenerator.nextInt(3, 15);
        int numberOfPassengers = randomGenerator.nextInt(1, 10);
        double commission = randomGenerator.nextInt(0, 100);
        LocalDateTime date = LocalDateTime.of(
                randomGenerator.nextInt(2000, 2024),
                randomGenerator.nextInt(1, 13),
                randomGenerator.nextInt(1, 29),
                randomGenerator.nextInt(0, 24),
                randomGenerator.nextInt(0, 60));
        Category category = createTestCategory();
        return new CarRide(guid, name, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, date, category);
    }

    public List<CarRide> createTestRides(int count) {
        return Stream
                .generate(this::createTestRide)
                .limit(count)
                .toList();
    }

    public List<Template> createTestTemplates(int count) {
        return Stream
                .generate(this::createTestTemplate)
                .limit(count)
                .toList();
    }

    public List<Category> createTestCategories(int count) {
        return Stream
                .generate(this::createTestCategory)
                .limit(count)
                .toList();
    }
}
