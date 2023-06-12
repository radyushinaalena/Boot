package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    double getSumSalaries();

    EmployeeDTO getEmployeeMinSalary();

    EmployeeDTO getEmployeeMaxSalary();

    List<EmployeeDTO> getEmployeesHighSalaries();


    EmployeeDTO getEmployeesById(Integer id) throws IOException;

    void addEmployee(EmployeeDTO employeeDTO) throws IOException;

    ;

    void deleteEmployeeById(Integer id) throws IOException;

    void editEmployeeById(Integer id, EmployeeDTO employeeDTO) throws IOException;

    List<EmployeeDTO> getEmployeesHighSalariesBySalary(Integer salary);

    List<EmployeeDTO> withHighestSalary();

    List<EmployeeDTO> getEmployeePosition(String position);

    EmployeeDTO getEmployeeFullInfo(int id);

    List<EmployeeDTO> getEmployeesFromPage(int page);

}
