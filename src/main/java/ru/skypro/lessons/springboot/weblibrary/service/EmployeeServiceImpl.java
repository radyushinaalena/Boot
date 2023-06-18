package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resources;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.emitter.EmitterException;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.*;

import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDTO employeeDTO;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeDTO employeeDTO) {
        this.employeeRepository = employeeRepository;
        this.employeeDTO = employeeDTO;
    }

    @PostConstruct
    public void init() {
        employeeRepository.deleteAll();
        employeeRepository.saveAll(
                List.of(
                        new Employee("Даша", 100000),
                        new Employee("Маша", 90000),
                        new Employee("Илья", 120000),
                        new Employee("Максим", 300000),
                        new Employee("Вика", 65000)
                )
        );
    }

    @Override
    public EmployeeDTO getEmployeesById(Integer id) throws IOException {
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmitterException("Сотрудника по данному не найдено"));

        return employeeDTO1.fromEmployee(employee);
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO1) {
        Employee employee = employeeDTO.toEmployee();

        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Integer id) throws IOException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow();
        employeeRepository.delete(employee);
    }

    @Override
    public void editEmployeeById(Integer id, EmployeeDTO employeeDTO) throws IOException {
        Employee employee = employeeDTO.toEmployee();
        employee.setId(id);
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeesHighSalariesBySalary(Integer salary) {
        return employeeRepository.findEmployeeBySalaryIsGreaterThan(salary)
                .stream()
                .map(employeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }


    @Override
    public double getSumSalaries() {
        return employeeRepository.sumSalaries();
    }

    @Override
    public EmployeeDTO getEmployeeMinSalary() {
        return employeeRepository.employeeMinSalary()
                .orElse(null);
    }

    @Override
    public EmployeeDTO getEmployeeMaxSalary() {
        List<EmployeeDTO> withHighestSalary = withHighestSalary();
        return withHighestSalary.get(0);
    }

    @Override
    public List<EmployeeDTO> getEmployeesHighSalaries() {
        int avg = employeeRepository.employeeHighSalary();
        return getEmployeesHighSalariesBySalary(avg);
    }


    @Override
    public List<EmployeeDTO> withHighestSalary() {
        return employeeRepository.employeeMaxSalary();
    }

    @Override
    public List<EmployeeDTO> getEmployeePosition(@Nullable String position) {
        return Optional.ofNullable(position)
                .map(employeeRepository::findEmployeeByPosition_Position)
                .orElseGet(employeeRepository::findAll)
                .stream()
                .map(employeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeFullInfo(int id) {
        return employeeRepository.findById(id)
                .map(employeeDTO::fromEmployee)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<EmployeeDTO> getEmployeesFromPage(int page) {
        return employeeRepository.findAll(PageRequest.of(page, 10))
                .stream()
                .map(employeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile file) {
        if(file!=null){
            ObjectMapper objectMapper = new ObjectMapper();

            List<EmployeeDTO> employeeDTOList =
                    objectMapper.readValue(file.getInputStream(), new TypeReference<List<EmployeeDTO>>(){});

            employeeRepository.saveAll(
                    employeeDTOList.stream().map(EmployeeDTO::toEmployee).toList());

        }else{
            System.out.println("Файл не найден");
        }

    }
}