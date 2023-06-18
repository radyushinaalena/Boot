package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary.dto.ReportDTO;

import java.util.List;


public interface ReportRepository extends CrudRepository<Report, Integer> {
    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto.ReportDTO " +
            "(p.position, COUNT(e), MAX(e.salary), MIN(e.salary), AVG(e.salary)) " +
            "FROM Position p JOIN Employee e on e.position.id = p.id  GROUP BY p.position")
    List<ReportDTO> getReportDTO();
}
