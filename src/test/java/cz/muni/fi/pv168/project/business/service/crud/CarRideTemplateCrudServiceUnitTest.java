package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.Validator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarRideTemplateCrudServiceUnitTest {

    private CarRideTemplateCrudService carRideTemplateCrudService;
    private Repository<Template> templateRepository;
    private Validator<Template> templateValidator;

    private static Template createTemplateInstance(String guid) {
        return new Template(
                guid,
                "Porsak",
                "skvela jizda",
                5.0,
                15.0,
                40.0,
                2,
                0.05,
                new Category("1", "Blue", 7),
                new Currency("2", "Czech crown", "Kc", 22.3),
                22.3
        );
    }

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        templateRepository = Mockito.mock(Repository.class);
        templateValidator = Mockito.mock(Validator.class);
        GuidProvider guidProvider = Mockito.mock(GuidProvider.class);
        carRideTemplateCrudService = new CarRideTemplateCrudService(templateRepository, templateValidator, guidProvider);
    }

    @Test
    void createWithGuidSucceeds() {
        var template = createTemplateInstance("e-1");

        when(templateValidator.validate(template))
                .thenReturn(ValidationResult.success());

        var result = carRideTemplateCrudService.create(template);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(templateRepository, times(1))
                .create(template);
    }

    @Test
    void createValidationError() {
        var template = createTemplateInstance("e-1");

        when(templateValidator.validate(template))
                .thenReturn(ValidationResult.failed("validation failed"));

        var result = carRideTemplateCrudService.create(template);

        assertThat(result).isEqualTo(ValidationResult.failed("validation failed"));
        verify(templateRepository, times(0))
                .create(template);
    }

    @Test
    void createFailsForDuplicateGuid() {
        var template = createTemplateInstance("e-1");

        when(templateValidator.validate(template))
                .thenReturn(ValidationResult.success());
        when(templateRepository.existsByGuid("e-1"))
                .thenReturn(true);

        assertThatExceptionOfType(EntityAlreadyExistsException.class)
                .isThrownBy(() -> carRideTemplateCrudService.create(template))
                .withMessage("cz.muni.fi.pv168.project.business.model.Template with given guid already exists: e-1");
    }

    @Test
    void updateWithGuidSucceeds() {
        var template = createTemplateInstance("e-1");

        when(templateValidator.validate(template))
                .thenReturn(ValidationResult.success());

        var result = carRideTemplateCrudService.update(template);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(templateRepository, times(1))
                .update(template);
    }

    @Test
    void updateValidationError() {
        var template = createTemplateInstance("e-1");

        when(templateValidator.validate(template))
                .thenReturn(ValidationResult.failed("validation failed"));

        var result = carRideTemplateCrudService.update(template);

        assertThat(result).isEqualTo(ValidationResult.failed("validation failed"));
        verify(templateRepository, times(0))
                .update(template);
    }

    @Test
    void deleteByGuid() {
        carRideTemplateCrudService.deleteByGuid("guid");

        verify(templateRepository, times(1))
                .deleteByGuid("guid");
    }

    @Test
    void findAll() {
        var expectedTemplateList = List.of(createTemplateInstance("e-1"));
        when(templateRepository.findAll())
                .thenReturn(expectedTemplateList);

        var foundTemplates = carRideTemplateCrudService.findAll();

        assertThat(foundTemplates).isEqualTo(expectedTemplateList);
    }

    @Test
    void deleteAll() {
        carRideTemplateCrudService.deleteAll();

        verify(templateRepository, times(1))
                .deleteAll();
    }
}
