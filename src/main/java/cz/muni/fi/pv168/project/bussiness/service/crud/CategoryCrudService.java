package cz.muni.fi.pv168.project.bussiness.service.crud;

import cz.muni.fi.pv168.project.bussiness.model.Category;
import cz.muni.fi.pv168.project.bussiness.model.GuidProvider;
import cz.muni.fi.pv168.project.bussiness.repository.Repository;
import cz.muni.fi.pv168.project.bussiness.service.validation.Validator;

public class CategoryCrudService extends ICrudServiceImpl<Category> implements ICategoryCrudService {

    public CategoryCrudService(Repository<Category> entityRepository, Validator<Category> entityValidator, GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
    }
}
