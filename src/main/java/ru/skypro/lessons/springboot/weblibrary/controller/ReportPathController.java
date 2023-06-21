package ru.skypro.lessons.springboot.weblibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.service.ReportPathService;

import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/reportPath")
@RequiredArgsConstructor
public class ReportPathController {

    private final ReportPathService reportPathService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Integer  addReportPath() throws IOException {
            return reportPathService.addReportPath();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Object getReportById(@PathVariable Integer id) {

        try {
            String fileName = "dataFile.json";
            String path = reportPathService.getReportPathById(id).getPath();
            File file = new File(path);
            Resource resource = new PathResource(file.getPath());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(resource);
        } catch (Throwable t) {
            return new ResponseEntity<>("Некорректный ID в БД.", HttpStatus.BAD_REQUEST);
        }

    }
}