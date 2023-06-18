package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.ReportPathDTO;
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
    public String uploadNewReportPath(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {

            ReportPathDTO reportPathDTO = new ReportPathDTO();
            int streamSize = inputStream.available();
            byte[] bytes = new byte[streamSize];
            inputStream.read(bytes);
            String json = new String(bytes, StandardCharsets.UTF_8);

            String fileName = "dataFile.json" + Math.random();
            Path path = Paths.get(fileName);
            try {
                Files.write(path, json.getBytes(StandardCharsets.UTF_8));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            reportPathDTO.setPath(path.toString());
            reportPathRepository.save(reportPathDTO.toReportPath());

            List<ReportPath> reportPathsList = new ArrayList<>();
            reportPathRepository.findAll().forEach((reportPathsList::add));
            ReportPath reportPath = reportPathsList.stream().max(Comparator.comparingInt(ReportPath::getId)).get();
            int lastId = reportPath.getId();
            String report = "id созданного объекта: ";
            return report + lastId;

        }
    }


    @Override
    public ReportPathDTO getReportPathById(int id) throws IllegalArgumentException {
        Optional<ReportPathDTO> reportOptional = reportPathRepository.findById(id).map(ReportPathDTO::fromReportPath);

        return reportOptional.orElseThrow(() -> new IllegalArgumentException("Некорректный ID отчета."));
    }
}