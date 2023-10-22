package cz.muni.fi.pv168.project.bussiness.service.crud;

import cz.muni.fi.pv168.project.bussiness.model.GuidProvider;
import cz.muni.fi.pv168.project.bussiness.model.Template;
import cz.muni.fi.pv168.project.bussiness.repository.Repository;
import cz.muni.fi.pv168.project.bussiness.service.validation.Validator;

public class CarRideTemplateCrudService extends ICrudServiceImpl<Template> implements ICarRideTempalteService {


    public CarRideTemplateCrudService(Repository<Template> entityRepository, Validator<Template> entityValidator, GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
    }
}
