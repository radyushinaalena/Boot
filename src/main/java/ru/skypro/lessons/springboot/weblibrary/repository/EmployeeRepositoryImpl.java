package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final List<Employee> employeeList = List.of(
            new Employee("Маша", 90000),
            new Employee("Даша", 100000),
            new Employee("Илья", 120000),
            new Employee("Максим", 300000),
            new Employee("Вика", 65000));

    @Override
    public int getSumSalaries() {
        int sum = 0;
        for (int i = 0; i < employeeList.size(); i++) {
            sum += employeeList.get(i).getSalary();
        }
        return sum;
    }

    ;

    @Override
    public String getEmployeeMinSalary() {
        int min = employeeList.get(0).getSalary();
        String minEmployee = employeeList.get(0).getName();
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            if (employee.getSalary() < min) {
                min = employee.getSalary();
                minEmployee = employee.getName();
            }
        }
        return minEmployee;
    }

    @Override
    public String getEmployeeMaxSalary() {
        int max = employeeList.get(0).getSalary();
        String maxEmployee = employeeList.get(0).getName();
        for (int n = 0; n < employeeList.size(); n++) {
            Employee employee2 = employeeList.get(n);
            if (employee2.getSalary() > max) {
                max = employee2.getSalary();
                maxEmployee = employee2.getName();
            }
        }
        return maxEmployee;
    }

    @Override
    public List<Employee> getEmployeesHighSalaries() {
        int averageSalary = getSumSalaries() / employeeList.size();
        List<Employee> employeesHighSalaries = new ArrayList<>();

        for (int j = 0; j < employeeList.size(); j++) {
            if (employeeList.get(j).getSalary() > averageSalary) {
                employeesHighSalaries.add(employeeList.get(j));
            }
        }
        return employeesHighSalaries;
    }

    ;

}