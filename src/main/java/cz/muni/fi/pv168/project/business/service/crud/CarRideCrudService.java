package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.Validator;
import cz.muni.fi.pv168.project.storage.InMemoryRepository;

import javax.inject.Inject;

public class CarRideCrudService extends ICrudServiceImpl<CarRide>{

    @Inject
    public CarRideCrudService(Repository<CarRide> entityRepository, Validator<CarRide> entityValidator, GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
    }
}
