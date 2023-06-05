package ru.skypro.lessons.springboot.weblibrary.pojo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/sum")
    public int SumSalaries() {
        return employeeService.getSumSalaries();
    }
    @GetMapping("/salary/min")
    public String EmployeeMinSalary() {
        return employeeService.getEmployeeMinSalary();
    }
    @GetMapping("/salary/max")
    public String EmployeeMaxSalary() {
        return employeeService.getEmployeeMaxSalary();
    }

    @GetMapping("/salary/high")
    public List<Employee> EmployeesHighSalaries() {
        return employeeService.getEmployeesHighSalaries();
    }
}