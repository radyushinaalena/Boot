package ru.skypro.lessons.springboot.weblibrary.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.skypro.lessons.springboot.weblibrary.service.EmployeeServiceImplTestConstants.*;

@SpringBootTest

@AutoConfigureMockMvc
@WithMockUser(roles = {"ADMIN", "USER"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeTest {

    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void contextLoads() {
    }

    @Test
    void getEmployeeWithCorrectId() throws Exception {
        int id = CORRECTED_ID;

        mockMvc.perform(get("/employee/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Даша"));
    }

    @Test
    void getEmployeeWithUncorrectedId() throws Exception {
        int id = UNCORRECTED_ID;

        mockMvc.perform(get("/employee/{id}", id))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getListOfEmployeeWithHighSalariesBySalary() throws Exception {
        mockMvc.perform(get("/employee/salary/high-salary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Даша"));
    }

    @Test
    void getListOfEmployeeWhisPage() throws Exception {
        mockMvc.perform(get("/employee/page?page=1&unitPerPage=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Маша"));
    }

    @Test
    void getEmployeeFullInfoWithCorrectId() throws Exception {
        int id = CORRECTED_ID;

        mockMvc.perform(get("/employee/{id}/fullInfo", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Даша"));
    }

    @Test
    void getEmployeeFullInfoWithUncorrectedId() throws Exception {
        int id = UNCORRECTED_ID;

        mockMvc.perform(get("/employee/{id}/fullInfo", id))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getListOfAllEmployee() throws Exception {
        mockMvc.perform(get("/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    void deleteEmployeeById() throws Exception {
        mockMvc.perform(delete("/admin/employee/" + CORRECTED_ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/1/fullInfo"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void editEmployeeById() throws Exception {
        int id = CORRECTED_ID;
        EmployeeDTO employeeDTO = EmployeeDTO.fromEmployee(EMPLOYEE1);

        mockMvc.perform(put("/admin/employee/" + id)
                        .content(objectMapper.writeValueAsString(employeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/{id}/fullInfo", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Test1"));
    }

    @Test
    void addEmployee() throws Exception {
        EmployeeDTO employeeDTO = EmployeeDTO.fromEmployee(EMPLOYEE1_ID);
        mockMvc.perform(get("/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(5));

        mockMvc.perform(post("/admin/employee/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(6));
    }

    @Test
    void uploadFile() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file.json",
                APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(EMPLOYEEDTOLIST_ID)
        );

        mockMvc.perform(MockMvcRequestBuilders.multipart("/admin/employee/upload")
                        .file(multipartFile)
                        .contentType(MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(7));
    }

}