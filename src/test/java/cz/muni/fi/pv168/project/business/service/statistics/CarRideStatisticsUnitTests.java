package cz.muni.fi.pv168.project.business.service.statistics;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarRideStatisticsUnitTests {
    private final ICarRideStatistics iCarRideStatistics = new CarRideStatistics();

    private final CarRide carRideOne = createCarRideOne("c-1");
    private final CarRide carRideTwo = createCarRideTwo("c-2");
    private final CarRide carRideThree = createCarRideThree("c-3");

    private final List<CarRide> emptyList = new ArrayList<>();
    private final List<CarRide> oneCarRideList = new ArrayList<>(List.of(carRideOne));
    private final List<CarRide> multipleCarRidesList = new ArrayList<>(List.of(carRideOne, carRideTwo, carRideThree));

    public static CarRide createCarRideOne(String guid) {
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
                new Currency("2", "Zloty", "zl", 3.97),
                3.97,
                LocalDateTime.now()

        );
    }

    public static CarRide createCarRideTwo(String guid) {
        return new CarRide(
                guid,
                "Porsak",
                "skvela jizda",
                80.0,
                20.0,
                35.0,
                2,
                0.10,
                new Category("1", "Blue", 7),
                new Currency("2", "Czech crown", "Kc", 22.3),
                22.3,
                LocalDateTime.of(2001, 1, 1, 11, 0, 0)
        );
    }

    public static CarRide createCarRideThree(String guid) {
        return new CarRide(
                guid,
                "Skoda",
                "skvela jizda",
                16.0,
                6.0,
                37.0,
                2,
                0.17,
                new Category("1", "Blue", 7),
                new Currency("2", "USD", "$", 1.0),
                1,
                LocalDateTime.of(2001, 1, 1, 11, 0, 0)
        );
    }

    @Test
    void zeroCarRideTotalDistance() {
        assertThat(iCarRideStatistics.getTotalDistance(emptyList)).isEqualTo(0.0);
    }

    @Test
    void zeroCarRideTotalExpenses() {
        assertThat(iCarRideStatistics.getTotalExpenses(emptyList)).isEqualTo(0);
    }

    @Test
    void zeroCarRideTodayExpenses() {
        assertThat(iCarRideStatistics.getTodayExpenses(emptyList)).isEqualTo(0.0);
    }

    @Test
    void zeroCarRideTotalRevenues() {
        assertThat(iCarRideStatistics.getTotalRevenues(emptyList)).isEqualTo(0.0);
    }

    @Test
    void zeroCarRideTotalRides() {
        assertThat(iCarRideStatistics.getTotalRides(emptyList)).isEqualTo(0);
    }

    @Test
    void oneCarRideTotalDistance() {
        assertThat(iCarRideStatistics.getTotalDistance(oneCarRideList)).isEqualTo(carRideOne.getDistance());
    }

    @Test
    void oneCarRideTotalExpenses() {
        assertThat(iCarRideStatistics.getTotalExpenses(oneCarRideList)).isEqualTo(carRideOne.getDistance() * carRideOne.getFuelConsumption() / 100 * carRideOne.getCostOfFuelPerLitreInDollars());
    }

    @Test
    void oneCarRideTodayExpenses() {
        assertThat(iCarRideStatistics.getTodayExpenses(oneCarRideList)).isEqualTo(carRideOne.getDistance() * carRideOne.getFuelConsumption() / 100 * carRideOne.getCostOfFuelPerLitreInDollars());
    }

    @Test
    void oneCarRideTotalRevenues() {
        assertThat(iCarRideStatistics.getTotalRevenues(oneCarRideList)).isEqualTo(carRideOne.getDistance() * carRideOne.getFuelConsumption() / 100 * carRideOne.getCostOfFuelPerLitreInDollars() * (1 + carRideOne.getCommission() / 100));
    }

    @Test
    void oneCarRideTotalRides() {
        assertThat(iCarRideStatistics.getTotalRides(oneCarRideList)).isEqualTo(1);
    }

    @Test
    void multipleCarRideTotalDistance() {
        assertThat(iCarRideStatistics.getTotalDistance(multipleCarRidesList)).isEqualTo(carRideOne.getDistance() + carRideTwo.getDistance() + carRideThree.getDistance());
    }

    @Test
    void multipleCarRideTotalExpenses() {
        assertThat(iCarRideStatistics.getTotalExpenses(multipleCarRidesList)).isEqualTo(
                carRideOne.getDistance() * carRideOne.getFuelConsumption() / 100 * carRideOne.getCostOfFuelPerLitreInDollars()
                        + carRideTwo.getDistance() * carRideTwo.getFuelConsumption() / 100 * carRideTwo.getCostOfFuelPerLitreInDollars()
                        + carRideThree.getDistance() * carRideThree.getFuelConsumption() / 100 * carRideThree.getCostOfFuelPerLitreInDollars());
    }

    @Test
    void multipleCarRideTodayExpenses() {
        assertThat(iCarRideStatistics.getTodayExpenses(multipleCarRidesList)).isEqualTo(carRideOne.getDistance() * carRideOne.getFuelConsumption() / 100 * carRideOne.getCostOfFuelPerLitreInDollars());
    }

    @Test
    void multipleCarRideTotalRevenues() {
        assertThat(iCarRideStatistics.getTotalRevenues(multipleCarRidesList)).isEqualTo(
                carRideOne.getDistance() * carRideOne.getFuelConsumption() / 100 * carRideOne.getCostOfFuelPerLitreInDollars() * (1 + carRideOne.getCommission() / 100)
                        + carRideTwo.getDistance() * carRideTwo.getFuelConsumption() / 100 * carRideTwo.getCostOfFuelPerLitreInDollars() * (1 + carRideTwo.getCommission() / 100)
                        + carRideThree.getDistance() * carRideThree.getFuelConsumption() / 100 * carRideThree.getCostOfFuelPerLitreInDollars() * (1 + carRideThree.getCommission() / 100));
    }

    @Test
    void multipleCarRideTotalRides() {
        assertThat(iCarRideStatistics.getTotalRides(multipleCarRidesList)).isEqualTo(3);
    }
}
