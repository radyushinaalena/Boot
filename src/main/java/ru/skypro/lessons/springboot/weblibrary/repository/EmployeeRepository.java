package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeRepository {
    public int getSumSalaries();
    public String getEmployeeMinSalary();
    public String getEmployeeMaxSalary();
    public List<Employee> getEmployeesHighSalaries();


}
