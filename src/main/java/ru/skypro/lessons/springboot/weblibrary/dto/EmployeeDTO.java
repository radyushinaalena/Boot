package ru.skypro.lessons.springboot.weblibrary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.pojo.Position;

import java.util.Optional;

@Component
public class EmployeeDTO {

    private Integer id;
    private String name;
    private Integer salary;
    private PositionDTO position;

    public EmployeeDTO fromEmployee(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setPosition(PositionDTO.fromPosition(employee.getPosition()));
        return employeeDTO;
    }
    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        employee.setPosition(this.getPosition().toPosition());
        return employee;
    }

    public EmployeeDTO(Integer id, String name, Integer salary, PositionDTO position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = position;
    }
    public EmployeeDTO() {
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}