package cz.muni.fi.pv168.employees.data;

import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TestDataGenerator {

    private static final Map<Integer, List<String>> FIRST_NAMES = Map.of(
            0, List.of("Jiří", "Jan", "Petr", "Josef", "Pavel", "Martin", "Tomáš", "Jaroslav", "Miroslav", "Zdeněk"),
            1, List.of("Jana", "Marie", "Eva", "Hana", "Anna", "Lenka", "Kateřina", "Lucie", "Věra", "Alena")
    );

    private static final Map<Integer, List<String>> LAST_NAMES = Map.of(
            0, List.of("Novák", "Novotný", "Dvořák", "Černý", "Procházka", "Šťastný", "Veselý", "Horák", "Němec", "Pokorný"),
            1, List.of("Nováková", "Novotná", "Dvořáková", "Černá", "Procházková", "Šťastná", "Veselá", "Horáková", "Němcová", "Pokorná")
    );

    private static final List<Department> DEPARTMENTS = List.of(
            new Department("IT", "007"),
            new Department("Sales", "666"),
            new Department("HR", "112")
    );

    private final Random random = new Random(2L);

    public Employee createTestEmployee() {
        int gender = random.nextInt(2);
        String firstName = selectRandom(FIRST_NAMES.get(gender));
        String lastName = selectRandom(LAST_NAMES.get(gender));
        Department department = selectRandom(DEPARTMENTS);
        return new Employee(firstName, lastName, department);
    }

    public List<Employee> createTestEmployees(int count) {
        return Stream
                .generate(this::createTestEmployee)
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Department> getDepartments() {
        return DEPARTMENTS;
    }

    private <T> T selectRandom(List<T> data) {
        int index = random.nextInt(data.size());
        return data.get(index);
    }
}
