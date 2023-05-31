package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private EmployeeService employeeService;

    private final List<Employee> employeeList = List.of(
            new Employee("Маша", 90000),
            new Employee("Даша", 100000),
            new Employee("Илья", 120000),
            new Employee("Максим", 300000),
            new Employee("Вика", 65000));

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }


}