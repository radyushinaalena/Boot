package ru.skypro.lessons.springboot.weblibrary.dto;

import ru.skypro.lessons.springboot.weblibrary.pojo.ReportPath;

import java.io.Serializable;

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



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}