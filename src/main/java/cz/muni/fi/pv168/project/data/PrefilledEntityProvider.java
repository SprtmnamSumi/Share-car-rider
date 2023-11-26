package cz.muni.fi.pv168.project.data;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.crud.ICrudService;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class PrefilledEntityProvider implements EntityProvider {
    private final Random randomGenerator = new Random();
    private final GuidProvider guidProvider;
    private final ICrudService<Category> categoryCrudService;
    private final ICrudService<Currency> currencyCrudService;

    @Inject
    public PrefilledEntityProvider(GuidProvider guidProvider,
                                   ICrudService<Category> categoryCrudService,
                                   ICrudService<Currency> templateCrudService1) {
        this.guidProvider = guidProvider;
        this.categoryCrudService = categoryCrudService;
        this.currencyCrudService = templateCrudService1;
    }

    @Override
    public CarRide getCarRide() {
        List<Category> categories = categoryCrudService.findAll();
        List<Currency> currencies = currencyCrudService.findAll();
        String name = "New Ride";
        String description = "";
        Double distance = randomGenerator.nextDouble(0, 1000);
        double fuelConsumption = randomGenerator.nextDouble(20, 40);
        int costOfFuelPerLitre = randomGenerator.nextInt(3, 15);
        int numberOfPassengers = randomGenerator.nextInt(1, 10);
        double commission = randomGenerator.nextInt(0, 100);
        LocalDateTime dateTime = LocalDateTime.now();
        Category category = categories.get(randomGenerator.nextInt(0, categories.size()));
        Currency currency = currencies.get(randomGenerator.nextInt(0, currencies.size()));
        return new CarRide(guidProvider.newGuid(), name, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers,
                commission, dateTime, category, currency, currency.getNewestRateToDollar());
    }

    @Override
    public Category getCategory() {
        List<Category> categories = categoryCrudService.findAll();
        Category category = categories.get(randomGenerator.nextInt(0, categories.size()));
        return new Category(guidProvider.newGuid(), "New Category", category.getColour());
    }

    @Override
    public Template getTemplate() {
        List<Category> categories = categoryCrudService.findAll();
        List<Currency> currencies = currencyCrudService.findAll();
        String name = "New Template";
        String description = "";
        Double distance = randomGenerator.nextDouble(0, 1000);
        double fuelConsumption = randomGenerator.nextDouble(20, 40);
        int costOfFuelPerLitre = randomGenerator.nextInt(3, 15);
        int numberOfPassengers = randomGenerator.nextInt(1, 10);
        double commission = randomGenerator.nextInt(0, 100);
        Category category = categories.get(randomGenerator.nextInt(0, categories.size()));
        Currency currency = currencies.get(randomGenerator.nextInt(0, currencies.size()));
        return new Template(guidProvider.newGuid(), name, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers,
                commission, category, currency, currency.getNewestRateToDollar());
    }

    @Override
    public Currency getCurrency() {
        List<Currency> currencies = currencyCrudService.findAll();
        Currency currency = currencies.get(randomGenerator.nextInt(0, currencies.size()));
        return new Currency("New Currency", currency.getSymbol(), currency.getNewestRateToDollar());
    }
}
