package cz.muni.fi.pv168.project.bussiness.service.crud;

import cz.muni.fi.pv168.project.bussiness.model.CarRide;
import cz.muni.fi.pv168.project.bussiness.model.GuidProvider;
import cz.muni.fi.pv168.project.bussiness.repository.Repository;
import cz.muni.fi.pv168.project.bussiness.service.validation.Validator;

public class CarRideCrudService extends ICrudServiceImpl<CarRide> implements ICarRideICrudService {


    public CarRideCrudService(Repository<CarRide> entityRepository, Validator<CarRide> entityValidator, GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
    }
}
