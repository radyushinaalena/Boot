package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface EmployeeRepository {
    public Map<Integer, Employee> getAllEmployees();

    public Employee getEmployeesById(Integer id) throws IOException;

    public void addEmployee(Employee employee) throws IOException;

    public void deleteEmployeeById(Integer id) throws IOException;

    public void editEmployeeById(Integer id, Employee employee) throws IOException;


}
