package ru.skypro.lessons.springboot.weblibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.io.IOException;

@RestController
@RequestMapping("/admin/employee")
@RequiredArgsConstructor
public class AdminEmployeeController {
    private final EmployeeService employeeService;


    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Integer id) throws IOException {
        employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/{id}")
    public void editEmployeeById(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) throws IOException {
        employeeService.editEmployeeById(id, employeeDTO);
    }

    @PostMapping("/")
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO) throws IOException {
        employeeService.addEmployee(employeeDTO);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile file){

        employeeService.uploadFile(file);
    }
}