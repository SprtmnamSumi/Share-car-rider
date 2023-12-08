package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryCrudServiceUnitTest {

    private CategoryCrudService categoryCrudService;
    private CarRideCrudService carRideCrudService;
    private Repository<Category> categoryRepository;
    private Validator<Category> categoryValidator;
    private Validator<CarRide> carRideValidator;
    private Repository<CarRide> carRideRepository;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        ICrudServiceImpl<CarRide> carRideICrudService = Mockito.mock(ICrudServiceImpl.class);
        categoryRepository = Mockito.mock(Repository.class);
        carRideRepository = Mockito.mock(Repository.class);
        categoryValidator = Mockito.mock(Validator.class);
        carRideValidator = Mockito.mock(Validator.class);
        GuidProvider guidProvider = Mockito.mock(GuidProvider.class);
        categoryCrudService = new CategoryCrudService(carRideICrudService, categoryRepository, categoryValidator, guidProvider);
        var carRideRepository = Mockito.mock(Repository.class);
        var carRideValidator = Mockito.mock(Validator.class);
        carRideCrudService = new CarRideCrudService(carRideRepository, carRideValidator, guidProvider);
    }

    @Test
    void createWithGuidSucceeds() {
        var category = createCategoryInstance("cat-1");

        when(categoryValidator.validate(category))
                .thenReturn(ValidationResult.success());

        var result = categoryCrudService.create(category);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(categoryRepository, times(1))
                .create(category);
    }

    @Test
    void createValidationError() {
        var category = createCategoryInstance("cat-1");

        when(categoryValidator.validate(category))
                .thenReturn(ValidationResult.failed("validation failed"));

        var result = categoryCrudService.create(category);

        assertThat(result).isEqualTo(ValidationResult.failed("validation failed"));
        verify(categoryRepository, times(0))
                .create(category);
    }

    @Test
    void createFailsForDuplicateGuid() {
        var category = createCategoryInstance("cat-1");

        when(categoryValidator.validate(category))
                .thenReturn(ValidationResult.success());
        when(categoryRepository.existsByGuid("cat-1"))
                .thenReturn(true);

        assertThatExceptionOfType(EntityAlreadyExistsException.class)
                .isThrownBy(() -> categoryCrudService.create(category))
                .withMessage("cz.muni.fi.pv168.project.business.model.Category with given guid already exists: cat-1");
    }

    @Test
    void updateWithGuidSucceeds() {
        var category = createCategoryInstance("cat-1");

        when(categoryValidator.validate(category))
                .thenReturn(ValidationResult.success());

        var result = categoryCrudService.update(category);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(categoryRepository, times(1))
                .update(category);
    }

    @Test
    void updateValidationError() {
        var category = createCategoryInstance("cat-1");

        when(categoryValidator.validate(category))
                .thenReturn(ValidationResult.failed("validation failed"));

        var result = categoryCrudService.update(category);

        assertThat(result).isEqualTo(ValidationResult.failed("validation failed"));
        verify(categoryRepository, times(0))
                .update(category);
    }

    @Test
    void deleteByGuid() {
        categoryCrudService.deleteByGuid("guid");

        verify(categoryRepository, times(1))
                .deleteByGuid("guid");
    }

    @Test
    void findAll() {
        var expectedCategoryList = List.of(createCategoryInstance("e-1"));
        when(categoryRepository.findAll())
                .thenReturn(expectedCategoryList);

        var foundCategories = categoryCrudService.findAll();

        assertThat(foundCategories).isEqualTo(expectedCategoryList);
    }

    @Test
    void deleteAll() {
        categoryCrudService.deleteAll();

        verify(categoryRepository, times(1))
                .deleteAll();
    }

    private static Category createCategoryInstance(String guid) {
        return new Category(
                guid,
                "Blue",
                5
        );
    }
}
