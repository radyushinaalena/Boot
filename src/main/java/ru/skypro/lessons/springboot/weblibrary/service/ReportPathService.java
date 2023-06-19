package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.ReportPathDTO;
import java.io.IOException;

public interface ReportPathService {
    Integer  addReportPath () throws IOException;

    ReportPathDTO getReportPathById(int id) throws IllegalArgumentException;

}
