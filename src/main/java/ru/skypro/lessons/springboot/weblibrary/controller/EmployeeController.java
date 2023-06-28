package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/sum")
    public double SumSalaries() {
        return employeeService.getSumSalaries();
    }

    @GetMapping("/salary/min")
    public EmployeeDTO EmployeeMinSalary() {
        return employeeService.getEmployeeMinSalary();
    }

    @GetMapping("/salary/max")
    public EmployeeDTO EmployeeMaxSalary() {
        return employeeService.getEmployeeMaxSalary();
    }

    @GetMapping("/salary/high")
    public List<EmployeeDTO> EmployeesHighSalaries() {
        return employeeService.getEmployeesHighSalaries();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeesById(@PathVariable Integer id) throws IOException {
        return employeeService.getEmployeesById(id);
    }

    @GetMapping("/salary/high-salary")
    public List<EmployeeDTO> getEmployeesHighSalariesBySalary(@RequestParam("salary") Integer salary) {
        return employeeService.getEmployeesHighSalariesBySalary(salary);
    }

    @GetMapping("withHighestSalary")
    public List<EmployeeDTO> salaryWithHighestSalary() {
        return employeeService.withHighestSalary();
    }

    @GetMapping
    public List<EmployeeDTO> getEmployeePosition(@RequestParam(required = false) String position) {
        return employeeService.getEmployeePosition(
                Optional.ofNullable(position)
                        .filter(p -> !p.isEmpty())
                        .orElse(null));
    }

    @GetMapping("/{id}/fullInfo")
    public EmployeeDTO getEmployeeFullInfo(@PathVariable int id) {
        return employeeService.getEmployeeFullInfo(id);
    }

    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeesFromPage(@RequestParam(required = false, defaultValue = "0") int page) {
        return employeeService.getEmployeesFromPage(page);

    }

}