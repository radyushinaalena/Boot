package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.exceptions.IdNotFoundException;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static ru.skypro.lessons.springboot.weblibrary.service.EmployeeServiceImplTestConstants.*;

@Testcontainers
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withUsername("postgres")
            .withPassword("admin");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
    @Mock
    private EmployeeRepository repositoryMock;

    @InjectMocks
    private EmployeeServiceImpl out;

    @Test
    @DisplayName("methodWhenAddEmployee")
    public void methodWhenAddEmployee() {
        when(repositoryMock.save(eq(EMPLOYEE1))).thenReturn(EMPLOYEE1);

        out.addEmployee(EmployeeDTO.fromEmployee(EMPLOYEE1));

        verify(repositoryMock, times(1)).save(EMPLOYEE1);
    }

    @SneakyThrows
    @Test
    @DisplayName("methodEditEmployeeById")
    public void methodEditEmployeeById() {
        when(repositoryMock.save(eq(EMPLOYEE1))).thenReturn(EMPLOYEE1);

        out.editEmployeeById(CORRECTED_ID, EmployeeDTO.fromEmployee(EMPLOYEE_ID));

        verify(repositoryMock, times(1)).save(EMPLOYEE1);

    }

    @DisplayName("methodGetEmployeeByNotCorrectId")
    @Test
    void methodGetEmployeeByNotCorrectId() {
        assertThrows(IdNotFoundException.class, () -> out.getEmployeesById(UNCORRECTED_ID));
        verify(repositoryMock, times(1)).findById(UNCORRECTED_ID);
    }


    @SneakyThrows
    @DisplayName("methodGetEmployeeByCorrectId")
    @Test
    void methodGetEmployeeByCorrectId() {
        EmployeeDTO expected = EmployeeDTO.fromEmployee(EMPLOYEE1);

        when(repositoryMock.findById(eq(CORRECTED_ID))).thenReturn(Optional.of(EMPLOYEE1));
        EmployeeDTO actual = out.getEmployeesById(CORRECTED_ID);

        assertEquals(expected, actual);

        verify(repositoryMock, times(1)).findById(CORRECTED_ID);

    }

    @DisplayName("methodGetEmployeesWithSalaryMoreThanCorrect")
    @Test
    void methodGetEmployeesWithSalaryMoreThanCorrect() {
        when(repositoryMock.findEmployeeBySalaryIsGreaterThan(eq(CORRECTED_SALARY))).thenReturn(EMPLOYEE_LIST);

        List<EmployeeDTO> actual = out.getEmployeesHighSalariesBySalary(CORRECTED_SALARY);

        assertIterableEquals(EMPLOYEEDTOLIST, actual);

        verify(repositoryMock, times(1)).findEmployeeBySalaryIsGreaterThan(anyInt());
    }

    @SneakyThrows
    @DisplayName("methodDeleteById")
    @Test
    void methodDeleteById() {
        doNothing().when(repositoryMock).deleteById(CORRECTED_ID);

        out.deleteEmployeeById(CORRECTED_ID);

        verify(repositoryMock, times(1)).deleteById(CORRECTED_ID);
    }

    @DisplayName("methodEmployeesByPageNumber")
    @Test
    void methodEmployeesByPageNumber() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(repositoryMock.findAll(eq(pageRequest))).thenReturn(new PageImpl<>(EMPLOYEE_LIST));

        List<EmployeeDTO> expected = out.getEmployeesFromPage(10);

        assertEquals(EMPLOYEEDTOLIST, expected);

        verify(repositoryMock, times(1)).findAll(pageRequest);
    }

    @SneakyThrows
    @DisplayName("methodAddEmployeesByFile")
    @Test
    void methodAddEmployeesByFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        json = objectMapper.writeValueAsString(EMPLOYEEDTOLIST);

        when(repositoryMock.saveAll(eq(EMPLOYEE_LIST))).thenReturn(any());

        out.uploadFile(new MockMultipartFile("test", json.getBytes()));

        verify(repositoryMock, times(1)).saveAll(EMPLOYEE_LIST);

    }
}