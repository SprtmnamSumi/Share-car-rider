package cz.muni.fi.pv168.project.data;


import cz.muni.fi.pv168.project.entities.CarRide;
import cz.muni.fi.pv168.project.entities.Template;
import cz.muni.fi.pv168.project.entities.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.temporal.ChronoUnit.DAYS;

public final class TestDataGenerator {


    private static final Map<List<String>, List<String>> NAMES = Map.of(
            List.of("Jiří", "Jan", "Petr", "Josef", "Pavel", "Martin", "Tomáš", "Jaroslav", "Miroslav", "Zdeněk",
                    "Jana", "Marie", "Eva", "Hana", "Anna", "Lenka", "Kateřina", "Lucie", "Věra", "Alena"),
            List.of("Novák", "Novotný", "Dvořák", "Černý", "Procházka", "Šťastný", "Veselý", "Horák", "Němec", "Pokorný",
                    "Nováková", "Novotná", "Dvořáková", "Černá", "Procházková", "Šťastná", "Veselá", "Horáková", "Němcová", "Pokorná")
    );
    private static final List<String> CATEGORY = List.of(
            "BMW", "Tesla", "Skoda", "Subaru", "Honda", "Bentley", "Autobus", "Helicopter helicopter", "Páracopter páracopter"
    );
    private static final List<String> CARNAME = List.of(
            "Karmen", "Ferda", "Luigiano", "Beatle", "S3XY"
    );
    private static final List<String> COLOR = List.of(
            "white", "black", "red", "blue", "green", "yellomello", "grey"
    );
    private static final LocalDate MIN_BIRTH_DATE = LocalDate.of(1950, JANUARY, 1);
    private static final LocalDate MAX_BIRTH_DATE = LocalDate.of(2002, DECEMBER, 31);
    private static final int upperbound = 1000000;
    private final Random random = new Random(2L);
    Random rand = new Random();
    private final int DISTANCE = rand.nextInt(upperbound);
    private final double DISTANCEDOUBLE = rand.nextDouble();
    //private static final LocalDate RANDOMDATE = new SimpleDateFormat("yyyyMMdd").format(new LocalDate[]);


    public Template createTestTemplate() {
        String title = "Test";
        String description = "Test";
        double distance = 100;
        int costOfFuelPerLitre = 100;
        int numberOfPassengers = 1;
        LocalDateTime date = LocalDateTime.of(2021, JANUARY, 1, 0, 0);
        Category category = crateTestCategory();
        return new Template(title, description, distance, DISTANCEDOUBLE, DISTANCE, costOfFuelPerLitre, numberOfPassengers, category);
    }


    public Category crateTestCategory() {
        var category = new Category("df", "fd");
        return category;
    }


    public CarRide createTestRide() {
        String title = "Test";
        String description = "Test";
        double distance = 100;
        int costOfFuelPerLitre = 100;
        int numberOfPassengers = 1;
        LocalDateTime date = LocalDateTime.of(2021, JANUARY, 1, 0, 0);
        Category category = crateTestCategory();
        return new CarRide(title, description, distance, DISTANCEDOUBLE, DISTANCE, costOfFuelPerLitre, numberOfPassengers, date, category);
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
                .generate(this::crateTestCategory)
                .limit(count)
                .toList();
    }

    private <T> T selectRandom(List<T> data) {
        int index = random.nextInt(data.size());
        return data.get(index);
    }

    private LocalDate selectRandomLocalDate(LocalDate min, LocalDate max) {
        int maxDays = Math.toIntExact(DAYS.between(min, max) + 1);
        int days = random.nextInt(maxDays);
        return min.plusDays(days);
    }
}
