package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    EmployeeDTO getEmployeesById(Integer id) throws IOException;

    void addEmployee(EmployeeDTO employeeDTO) throws IOException;

    void deleteEmployeeById(Integer id) throws IOException;

    void editEmployeeById(Integer id, EmployeeDTO employeeDTO) throws IOException;

    List<EmployeeDTO> getEmployeesHighSalariesBySalary(Integer salary);

    List<EmployeeDTO> getEmployeesFromPage(int page);

    EmployeeDTO getEmployeeFullInfo(int id);
    void uploadFile(MultipartFile file);
}