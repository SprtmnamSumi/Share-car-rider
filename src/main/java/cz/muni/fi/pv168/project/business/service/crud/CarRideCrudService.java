package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.Validator;

import javax.inject.Inject;

class CarRideCrudService extends ICrudServiceImpl<CarRide> {

    @Inject
    CarRideCrudService(Repository<CarRide> entityRepository, Validator<CarRide> entityValidator, GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
    }
}
