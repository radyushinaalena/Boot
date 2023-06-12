package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    int getSumSalaries();

    String getEmployeeMinSalary();

    String getEmployeeMaxSalary();

    Map<Integer, Employee> getEmployeesHighSalaries();

    public Map<Integer, Employee> getAllEmployees();

    public Employee getEmployeesById(Integer id) throws IOException;

    public void addEmployee(Employee employee) throws IOException;

    public void deleteEmployeeById(Integer id) throws IOException;

    public void editEmployeeById(Integer id, Employee employee) throws IOException;

    public Map<Integer, Employee> getEmployeesHighSalariesBySalary(Integer salary);

}
