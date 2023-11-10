package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.CurrencyConversion;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.Validator;

import javax.inject.Inject;

class ConversionCrudService extends ICrudServiceImpl<CurrencyConversion> implements IConversionCrudService {
    @Inject
    ConversionCrudService(Repository<CurrencyConversion> entityRepository, Validator<CurrencyConversion> entityValidator, GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
    }
}
