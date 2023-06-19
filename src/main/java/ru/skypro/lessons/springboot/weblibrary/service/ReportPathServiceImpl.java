package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.ReportPathDTO;
import ru.skypro.lessons.springboot.weblibrary.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary.pojo.ReportPath;
import ru.skypro.lessons.springboot.weblibrary.repository.ReportPathRepository;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class ReportPathServiceImpl implements ReportPathService {

    private final ReportPathRepository reportPathRepository;

    public ReportPathServiceImpl(ReportPathRepository reportPathRepository) {
        this.reportPathRepository = reportPathRepository;
    }

    @Override
    public Integer  addReportPath() throws IOException {
        String fileName = "dataFile.json" + Math.random();
        String json = String.valueOf(reportPathRepository.addReportPath());
        Path path = Paths.get(fileName);
        try {
            Files.writeString(path, json);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        ReportPath reportPath = new ReportPath();
        reportPath.setPath(String.valueOf(path.toAbsolutePath()));
        reportPathRepository.save(reportPath);
        return reportPath.getId();
    }


    @Override
    public ReportPathDTO getReportPathById(int id) throws IllegalArgumentException {
        Optional<ReportPathDTO> reportOptional = reportPathRepository.findById(id).map(ReportPathDTO::fromReportPath);

        return reportOptional.orElseThrow(() -> new IllegalArgumentException("Некорректный ID отчета."));
    }
}