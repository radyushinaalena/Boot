package ru.skypro.lessons.springboot.weblibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.lessons.springboot.weblibrary.pojo.ReportPath;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Builder(toBuilder = true)
public class ReportPathDTO implements Serializable {
    private int id;
    private String path;


    public static ReportPathDTO fromReportPath(ReportPath reportPath) {
        ReportPathDTO reportPathDTO = new ReportPathDTO();
        reportPathDTO.setId(reportPath.getId());
        reportPathDTO.setPath(reportPath.getPath());
        return reportPathDTO;
    }

    public ReportPath toReportPath() {
        ReportPath reportPath = new ReportPath();
        reportPath.setId(this.getId());
        reportPath.setPath(this.getPath());
        return reportPath;
    }
}