package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public int getSumSalaries() {
        int sum = 0;
        for (int i = 0; i < getAllEmployees().size(); i++) {
            sum += employeeRepository.getAllEmployees().get(i).getSalary();
        }
        return sum;
    }

    ;

    @Override
    public String getEmployeeMinSalary() {
        int min = getAllEmployees().get(0).getSalary();
        String minEmployee = getAllEmployees().get(0).getName();
        for (int i = 0; i < getAllEmployees().size(); i++) {
            Employee employee = getAllEmployees().get(i);
            if (employee.getSalary() < min) {
                min = employee.getSalary();
                minEmployee = employee.getName();
            }
        }
        return minEmployee;
    }

    @Override
    public String getEmployeeMaxSalary() {
        int max = getAllEmployees().get(0).getSalary();
        String maxEmployee = getAllEmployees().get(0).getName();
        for (int n = 0; n < getAllEmployees().size(); n++) {
            Employee employee2 = getAllEmployees().get(n);
            if (employee2.getSalary() > max) {
                max = employee2.getSalary();
                maxEmployee = employee2.getName();
            }
        }
        return maxEmployee;
    }

    @Override
    public List<Employee> getEmployeesHighSalaries() {
        int averageSalary = getSumSalaries() / getAllEmployees().size();
        List<Employee> employeesHighSalaries = new ArrayList<>();

        for (int j = 0; j < getAllEmployees().size(); j++) {
            if (getAllEmployees().get(j).getSalary() > averageSalary) {
                employeesHighSalaries.add(getAllEmployees().get(j));
            }
        }
        return employeesHighSalaries;
    }

}