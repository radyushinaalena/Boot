package ru.skypro.lessons.springboot.weblibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.service.ReportService;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/")
    public Integer addReport(){
        return reportService.addReport();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getReportById(@PathVariable Integer id){
        return reportService.downloadFile(id);
    }
}