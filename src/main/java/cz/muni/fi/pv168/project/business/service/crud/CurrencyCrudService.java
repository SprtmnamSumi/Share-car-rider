package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.Validator;

import javax.inject.Inject;

class CurrencyCrudService extends ICrudServiceImpl<Currency> {
    @Inject
    CurrencyCrudService(Repository<Currency> entityRepository, Validator<Currency> entityValidator, GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
    }
}
