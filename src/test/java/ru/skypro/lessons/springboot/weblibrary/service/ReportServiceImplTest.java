package ru.skypro.lessons.springboot.weblibrary.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import ru.skypro.lessons.springboot.weblibrary.dto.ReportPathDTO;
import ru.skypro.lessons.springboot.weblibrary.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary.repository.ReportPathRepository;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Testcontainers
@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {
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
    private ReportPathRepository reportPathRepositoryMock;
    @Mock
    private ReportPathServiceImpl reportPathServiceMock;
    @Mock
    private Report reportMock;
    @InjectMocks
    private ReportPathServiceImpl reportPathServiceTest;
    private ReportServiceImpl reportServiceTest;

    @DisplayName("methodAddReportCorrect")
    @Test
    void methodAddReportCorrect() {

        List<ReportPathDTO> reportPathDTOS = new ArrayList<>();
        ReportPathDTO reportPathDTO = ReportPathDTO.builder()
                .id(1)
                .path("Test")
                .build();
        reportPathDTOS.add(reportPathDTO);
        when(reportPathRepositoryMock.addReportPath()).thenReturn(reportPathDTOS);
        assertEquals(1,reportPathRepositoryMock.count());
        verify(reportPathRepositoryMock, times(1)).addReportPath();
    }

    @DisplayName("methodDownloadFileCorrect")
    @Test
    @ValueSource(ints = {1, 3, 5})
    void methodDownloadFileCorrect(Integer id) {
        ResponseEntity<Report> mockResponse = mock(ResponseEntity.class);
        when(mockResponse.getBody()).thenReturn(reportMock);
        ResponseEntity<Resource> result = reportServiceTest.downloadFile(id);
        assertEquals(mockResponse, result);
        assertEquals(reportMock, result.getBody());
    }
}