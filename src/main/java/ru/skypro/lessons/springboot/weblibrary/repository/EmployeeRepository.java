package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT e FROM Employee e")
    List<Employee> findAllEmployee();
    @Query("SELECT SUM(e.salary) from  Employee e")
    double sumSalaries();

    @Query("SELECT AVG(e.salary) from  Employee e")
    int employeeHighSalary();

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO(e.id, e.name, e.salary, p.position) FROM Employee  e left join Position p on p.id = e.position.id where  e.salary = (SELECT MIN(e.salary) from  Employee e)")
    Optional<EmployeeDTO> employeeMinSalary();

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO(e.id, e.name, e.salary, p.position) FROM Employee  e left join Position p on p.id = e.position.id where e.salary = (SELECT MAX(e.salary) from  Employee e)")
    List<EmployeeDTO> employeeMaxSalary();

    List<Employee> findEmployeeBySalaryIsGreaterThan(Integer salary);

    List<Employee> findEmployeeByPosition_Position(String position);

}
