package cz.muni.fi.pv168.project.business.repository;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.storage.memory.InMemoryRepository;
import cz.muni.fi.pv168.project.storage.sql.SqlRepository;
import cz.muni.fi.pv168.project.storage.sql.entity.CategoryEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.CurrencyEntity;
import cz.muni.fi.pv168.project.storage.sql.entity.TemplateEntity;
import java.util.ArrayList;

public class Module extends AbstractModule {
    @Override
    protected void configure() {


        bind(new TypeLiteral<Repository<CarRide>>() {
        }).toInstance(new InMemoryRepository<>(new ArrayList<CarRide>()));

        bind(new TypeLiteral<Repository<Template>>() {
        }).to(new TypeLiteral<SqlRepository<Template, TemplateEntity>>() {
        });
        bind(new TypeLiteral<Repository<Category>>() {
        }).to(new TypeLiteral<SqlRepository<Category, CategoryEntity>>() {
        });
        bind(new TypeLiteral<Repository<Currency>>() {
        }).to(new TypeLiteral<SqlRepository<Currency, CurrencyEntity>>() {
        });
    }
}
