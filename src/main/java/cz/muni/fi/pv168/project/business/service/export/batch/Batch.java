package cz.muni.fi.pv168.project.business.service.export.batch;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;

import java.util.Collection;


public record Batch(Collection<CarRide> carRides, Collection<Category> categories, Collection<Currency> currencies) {
}
