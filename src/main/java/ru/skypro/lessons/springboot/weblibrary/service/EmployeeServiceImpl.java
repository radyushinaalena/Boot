package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Map<Integer, Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Employee getEmployeesById(Integer id) throws IOException {
        return employeeRepository.getEmployeesById(id);
    }

    @Override
    public void addEmployee(Employee employee) throws IOException {
        employeeRepository.addEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(Integer id) throws IOException {
        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public void editEmployeeById(Integer id, Employee employee) throws IOException {
        employeeRepository.editEmployeeById(id, employee);
    }

    @Override
    public Map<Integer, Employee> getEmployeesHighSalariesBySalary(Integer salary) {
        List<Map.Entry<Integer, Employee>> list = new ArrayList<>(getAllEmployees().entrySet());
        Map<Integer, Employee> employeesHighSalaries = new HashMap<>();

        for (int j = 0; j < list.size(); j++) {

            if (list.get(j).getValue().getSalary() > salary) {
                employeesHighSalaries.put(j, list.get(j).getValue());
            }
        }
        return employeesHighSalaries;
    }

    @Override
    public int getSumSalaries() {
        int sum = 0;
        List<Map.Entry<Integer, Employee>> list = new ArrayList<>(getAllEmployees().entrySet());

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getValue().getSalary();
        }
        return sum;
    }

    ;

    @Override
    public String getEmployeeMinSalary() {
        List<Map.Entry<Integer, Employee>> list = new ArrayList<>(getAllEmployees().entrySet());
        int min = list.get(0).getValue().getSalary();
        String minEmployee = list.get(0).getValue().getName();
        for (int i = 0; i < list.size(); i++) {
            Employee employee = list.get(i).getValue();
            if (employee.getSalary() < min) {
                min = employee.getSalary();
                minEmployee = employee.getName();
            }
        }
        return minEmployee;
    }

    @Override
    public String getEmployeeMaxSalary() {
        List<Map.Entry<Integer, Employee>> list = new ArrayList<>(getAllEmployees().entrySet());
        int max = list.get(0).getValue().getSalary();
        String maxEmployee = list.get(0).getValue().getName();
        for (int n = 0; n < list.size(); n++) {
            Employee employee2 = list.get(n).getValue();
            if (employee2.getSalary() > max) {
                max = employee2.getSalary();
                maxEmployee = employee2.getName();
            }
        }
        return maxEmployee;
    }

    @Override
    public Map<Integer, Employee> getEmployeesHighSalaries() {
        List<Map.Entry<Integer, Employee>> list = new ArrayList<>(getAllEmployees().entrySet());
        int averageSalary = getSumSalaries() / list.size();
        Map<Integer, Employee> employeesHighSalaries = new HashMap<>();
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).getValue().getSalary() > averageSalary) {
                employeesHighSalaries.put(j, list.get(j).getValue());
            }
        }
        return employeesHighSalaries;
    }

}