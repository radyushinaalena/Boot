package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public int getSumSalaries() {

        return employeeRepository.getSumSalaries();
    }

    @Override
    public String getEmployeeMinSalary() {
        return employeeRepository.getEmployeeMinSalary();
    }

    @Override
    public String getEmployeeMaxSalary() {
        return employeeRepository.getEmployeeMaxSalary();
    }

    @Override
    public List<Employee> getEmployeesHighSalaries() {
        return employeeRepository.getEmployeesHighSalaries();
    }
}