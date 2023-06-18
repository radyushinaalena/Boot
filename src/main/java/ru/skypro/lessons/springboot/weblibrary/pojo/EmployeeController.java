package ru.skypro.lessons.springboot.weblibrary.pojo;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/all")
    public Map<Integer, Employee> showCounter(){
        return employeeService.getAllEmployees();
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
    public Map<Integer, Employee> EmployeesHighSalaries() {
        return employeeService.getEmployeesHighSalaries();
    }

    @GetMapping("/{id}")
    public Employee getEmployeesById(@PathVariable Integer id) throws IOException {
        return employeeService.getEmployeesById(id);
    }

    @GetMapping("/salary/high-salary")
    public Map<Integer, Employee> getEmployeesHighSalariesBySalary(@RequestParam("salary") Integer salary) {
        return employeeService.getEmployeesHighSalariesBySalary(salary);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Integer id) throws IOException {
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping("/")
    public void addEmployee(@RequestBody Employee employee) throws IOException {
        employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public void editEmployeeById(@PathVariable Integer id, @RequestBody Employee employee) throws IOException {
        employeeService.editEmployeeById(id, employee);
    }
}