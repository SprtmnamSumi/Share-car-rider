package cz.muni.fi.pv168.project.business.service.export.batch;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;

import java.util.Collection;

/**
 * @author Sabrina Orálková, 525089
 */
public record Batch(Collection<CarRide> carRides, Collection<Category> categories, Collection<Currency> currencies) {
}
