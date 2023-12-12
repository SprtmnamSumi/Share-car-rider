package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("SpellCheckingInspection")
public class CarRideCrudServiceUnitTest {

    private CarRideCrudService carRideCrudService;
    private Repository<CarRide> carRideRepository;
    private Validator<CarRide> carRideValidator;

    public static CarRide createCarRideInstance(String guid) {
        return new CarRide(
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
                LocalDateTime.now()
        );
    }

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        carRideRepository = Mockito.mock(Repository.class);
        carRideValidator = Mockito.mock(Validator.class);
        GuidProvider guidProvider = Mockito.mock(GuidProvider.class);
        carRideCrudService = new CarRideCrudService(carRideRepository, carRideValidator, guidProvider);
    }

    @Test
    void createWithGuidSucceeds() {
        var carRide = createCarRideInstance("c-1");

        when(carRideValidator.validate(carRide))
                .thenReturn(ValidationResult.success());

        var result = carRideCrudService.create(carRide);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(carRideRepository, times(1))
                .create(carRide);
    }

    @Test
    void createValidationError() {
        var carRide = createCarRideInstance("c-1");

        when(carRideValidator.validate(carRide))
                .thenReturn(ValidationResult.failed("validation failed"));

        var result = carRideCrudService.create(carRide);

        assertThat(result).isEqualTo(ValidationResult.failed("validation failed"));
        verify(carRideRepository, times(0))
                .create(carRide);
    }

    @Test
    void createFailsForDuplicateGuid() {
        var carRide = createCarRideInstance("c-1");

        when(carRideValidator.validate(carRide))
                .thenReturn(ValidationResult.success());
        when(carRideRepository.existsByGuid("c-1"))
                .thenReturn(true);

        assertThatExceptionOfType(EntityAlreadyExistsException.class)
                .isThrownBy(() -> carRideCrudService.create(carRide))
                .withMessage("cz.muni.fi.pv168.project.business.model.CarRide with given guid already exists: c-1");
    }

    @Test
    void updateWithGuidSucceeds() {
        var carRide = createCarRideInstance("c-1");

        when(carRideValidator.validate(carRide))
                .thenReturn(ValidationResult.success());

        var result = carRideCrudService.update(carRide);

        assertThat(result).isEqualTo(ValidationResult.success());
        verify(carRideRepository, times(1))
                .update(carRide);
    }

    @Test
    void updateValidationError() {
        var carRide = createCarRideInstance("c-1");

        when(carRideValidator.validate(carRide))
                .thenReturn(ValidationResult.failed("validation failed"));

        var result = carRideCrudService.update(carRide);

        assertThat(result).isEqualTo(ValidationResult.failed("validation failed"));
        verify(carRideRepository, times(0))
                .update(carRide);
    }

    @Test
    void deleteByGuid() {
        carRideCrudService.deleteByGuid("guid");

        verify(carRideRepository, times(1))
                .deleteByGuid("guid");
    }

    @Test
    void findAll() {
        var expectedCarRideList = List.of(createCarRideInstance("c-1"));
        when(carRideRepository.findAll())
                .thenReturn(expectedCarRideList);

        var foundCarRides = carRideCrudService.findAll();

        assertThat(foundCarRides).isEqualTo(expectedCarRideList);
    }

    @Test
    void deleteAll() {
        carRideCrudService.deleteAll();

        verify(carRideRepository, times(1))
                .deleteAll();
    }
}
