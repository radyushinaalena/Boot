package ru.skypro.lessons.springboot.weblibrary.pojo;

public class Employee {
    private String name;
    private int salary;
    private Integer id;

    public Employee(Integer id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public Integer getId() {
        return id;
    }
}
