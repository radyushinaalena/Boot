package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeService {
     int getSumSalaries();

     String getEmployeeMinSalary();
     String getEmployeeMaxSalary();
     List<Employee> getEmployeesHighSalaries();

}
