package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary.projections.EmployeeInfo;


import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImplTestConstants {
    public static final Position POSITION1 = new Position(1, "Test1");
    public static final Position POSITION2 = new Position(2, "Test2");
    public static final Employee EMPLOYEE1 = new Employee(1, "EmployeeTest1", 5_000, POSITION1 );
    public static final Employee EMPLOYEE2 = new Employee(2, "EmployeeTest2", 10_000, POSITION2);


    public static final List<Employee> EMPLOYEE_LIST = new ArrayList<>(){{
        add(EMPLOYEE1);
        add(EMPLOYEE2);
    }};
    public static final List<EmployeeInfo> EMPLOYEE_LIST2 = new ArrayList<>(){{
        add(new EmployeeInfo(EMPLOYEE1.getName(), EMPLOYEE1.getSalary()));
        add(new EmployeeInfo(EMPLOYEE2.getName(),EMPLOYEE2.getSalary()));
    }};

    public static final EmployeeInfo EMPLOYEEINFO1 = new EmployeeInfo(EMPLOYEE1.getName(), EMPLOYEE1.getSalary());

    public static final List<EmployeeDTO> EMPLOYEEDTOLIST = EMPLOYEE_LIST.stream().map(EmployeeDTO::fromEmployee).toList();
    public static final Employee EMPLOYEE_ID = new Employee(null,"Test", 5_000, POSITION1);

    public static final int CORRECTED_ID = 1;
    public static final int UNCORRECTED_ID = -1;

    public static final int CORRECTED_SALARY = 5_000;





}