package ru.skypro.lessons.springboot.weblibrary.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeInfo {

    private String name;
    private int salary;

}
