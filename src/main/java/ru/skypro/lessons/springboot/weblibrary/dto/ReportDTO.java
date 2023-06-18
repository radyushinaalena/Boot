package ru.skypro.lessons.springboot.weblibrary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ReportDTO {
    private String position;
    private Long numbersOfEmployees;
    private Integer maxSalary;
    private Integer minSalary;
    private Double averageSalary;

}
