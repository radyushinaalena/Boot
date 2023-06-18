package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {


    private final Map<Integer, Employee> employeeMap = new HashMap<>();

    {
        employeeMap.put(1, new Employee(1, "Маша", 90000));
        employeeMap.put(2, new Employee(2, "Даша", 100000));
        employeeMap.put(3, new Employee(3, "Илья", 120000));
        employeeMap.put(4, new Employee(4, "Максим", 300000));
        employeeMap.put(5, new Employee(5, "Вика", 65000));
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {
        return employeeMap;
    }

    @Override

    public Employee getEmployeesById(Integer id) throws IOException {
        if (id > getAllEmployees().size() || id <= 0) {
            throw new IOException("Неверно передан id работника");
        }
        return employeeMap.get(id);
    }

    @Override
    public void addEmployee(Employee employee) throws IOException {
        if (employee.getId() <= getAllEmployees().size()) {
            throw new IOException("Введен некорректный id работника или такой id уже существует");
        }
        employeeMap.put(employee.getId(), employee);
    }

    @Override
    public void deleteEmployeeById(Integer id) throws IOException {
        if (id > getAllEmployees().size() || id <= 0) {
            throw new IOException("Неверно передан id работника");
        }
        employeeMap.remove(id);
    }

    @Override
    public void editEmployeeById(Integer id, Employee employee) throws IOException {
        if (id > getAllEmployees().size() || id <= 0) {
            throw new IOException("Неверно передан id работника");
        } else {
            employeeMap.remove(id);
            employeeMap.put(id, employee);
        }
    }

}