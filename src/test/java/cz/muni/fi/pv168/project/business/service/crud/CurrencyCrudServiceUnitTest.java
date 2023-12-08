package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.Validator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CurrencyCrudServiceUnitTest {
    private CurrencyCrudService currencyCrudService;
    private Repository<Currency> currencyRepository;
    private Validator<Currency> currencyValidator;

    private static Currency createCurrencyInstance() {
        return new Currency(
                "2",
                "USD",
                "$",
                22.3
        );
    }

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        currencyRepository = Mockito.mock(Repository.class);
        currencyValidator = Mockito.mock(Validator.class);
        GuidProvider guidProvider = Mockito.mock(GuidProvider.class);
        currencyCrudService = new CurrencyCrudService(currencyRepository, currencyValidator, guidProvider);
    }

    @Test
    void createWithGuidSucceeds() {
        var currency = createCurrencyInstance();

        when(currencyValidator.validate(currency))
                .thenReturn(ValidationResult.success());

        var result = currencyCrudService.create(currency);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(currencyRepository, times(1))
                .create(currency);
    }

    @Test
    void createValidationError() {
        var currency = createCurrencyInstance();

        when(currencyValidator.validate(currency))
                .thenReturn(ValidationResult.failed("validation failed"));

        var result = currencyCrudService.create(currency);

        assertThat(result).isEqualTo(ValidationResult.failed("validation failed"));
        verify(currencyRepository, times(0))
                .create(currency);
    }

    @Test
    void updateWithGuidSucceeds() {
        var currency = createCurrencyInstance();

        when(currencyValidator.validate(currency))
                .thenReturn(ValidationResult.success());

        var result = currencyCrudService.update(currency);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(currencyRepository, times(1))
                .update(currency);
    }

    @Test
    void updateValidationError() {
        var currency = createCurrencyInstance();

        when(currencyValidator.validate(currency))
                .thenReturn(ValidationResult.failed("validation failed"));

        var result = currencyCrudService.update(currency);

        assertThat(result).isEqualTo(ValidationResult.failed("validation failed"));
        verify(currencyRepository, times(0))
                .update(currency);
    }

    @Test
    void deleteByGuid() {
        currencyCrudService.deleteByGuid("guid");

        verify(currencyRepository, times(1))
                .deleteByGuid("guid");
    }

    @Test
    void findAll() {
        var expectedCurrencyList = List.of(createCurrencyInstance());
        when(currencyRepository.findAll())
                .thenReturn(expectedCurrencyList);

        var foundCurrencies = currencyCrudService.findAll();

        assertThat(foundCurrencies).isEqualTo(expectedCurrencyList);
    }

    @Test
    void deleteAll() {
        currencyCrudService.deleteAll();

        verify(currencyRepository, times(1))
                .deleteAll();
    }
}
