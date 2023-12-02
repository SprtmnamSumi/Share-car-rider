package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.Validator;

import javax.inject.Inject;

public class CarRideTemplateCrudService extends ICrudServiceImpl<Template>{

    @Inject
    public CarRideTemplateCrudService(Repository<Template> entityRepository, Validator<Template> entityValidator, GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
    }
}
