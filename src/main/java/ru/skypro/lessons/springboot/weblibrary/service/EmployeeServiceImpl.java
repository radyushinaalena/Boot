package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.emitter.EmitterException;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDTO employeeDTO;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeDTO employeeDTO) {
        this.employeeRepository = employeeRepository;
        this.employeeDTO = employeeDTO;
    }

    @PostConstruct
    public void init() {
        employeeRepository.deleteAll();
        employeeRepository.saveAll(
                List.of(
                        new Employee("Даша", 100000),
                        new Employee("Маша", 90000),
                        new Employee("Илья", 120000),
                        new Employee("Максим", 300000),
                        new Employee("Вика", 65000)
                )
        );
    }

    @Override
    public EmployeeDTO getEmployeesById(Integer id) throws IOException {
        logger.info("Was invoked method for getting all employees by id");
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmitterException("Сотрудника по данному не найдено"));
        logger.debug("Database was accessed successfully");
        return employeeDTO1.fromEmployee(employee);
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        logger.info("Was invoked method for create employees");

        Employee employee = employeeDTO.toEmployee();

        employeeRepository.save(employee);
        logger.debug("Database was accessed successfully");
    }


    @Override
    public void deleteEmployeeById(Integer id) throws IOException {
        logger.info("Was invoked method for deleted employees by id");

        Employee employee = employeeRepository.findById(id)
                .orElseThrow();
        employeeRepository.delete(employee);
        logger.debug("Database was accessed successfully");
    }

    @Override
    public void editEmployeeById(Integer id, EmployeeDTO employeeDTO) throws IOException {
        logger.info("Was invoked method for update employees by id");
        Employee employee = employeeDTO.toEmployee();
        employee.setId(id);
        employeeRepository.save(employee);
        logger.debug("Database was accessed successfully");
    }

    @Override
    public List<EmployeeDTO> getEmployeesHighSalariesBySalary(Integer salary)
    {logger.info("Was invoked method for getting employee with high salary by salary");
        return employeeRepository.findEmployeeBySalaryIsGreaterThan(salary)
                .stream()
                .map(employeeDTO::fromEmployee)
                .collect(Collectors.toList());

    }


    @Override
    public double getSumSalaries() {
        logger.info("Was invoked method for sum salary");
        return employeeRepository.sumSalaries();
    }

    @Override
    public EmployeeDTO getEmployeeMinSalary() {
        logger.info("Was invoked method for min salary");
        return employeeRepository.employeeMinSalary()
                .orElse(null);

    }

    @Override
    public EmployeeDTO getEmployeeMaxSalary() {
        logger.info("Was invoked method for max salary");
        List<EmployeeDTO> withHighestSalary = withHighestSalary();
        logger.debug("Database was accessed successfully");
        return withHighestSalary.get(0);
    }

    @Override
    public List<EmployeeDTO> getEmployeesHighSalaries() {
        logger.info("Was invoked method for getting employee with salary");
        int avg = employeeRepository.employeeHighSalary();
        logger.debug("Database was accessed successfully");
        return getEmployeesHighSalariesBySalary(avg);
    }


    @Override
    public List<EmployeeDTO> withHighestSalary() {
        logger.info("Was invoked method for employee with salary");
        return employeeRepository.employeeMaxSalary();
    }

    @Override
    public List<EmployeeDTO> getEmployeePosition(@Nullable String position) {
        logger.info("Was invoked method for position employee");
        return Optional.ofNullable(position)
                .map(employeeRepository::findEmployeeByPosition_Position)
                .orElseGet(employeeRepository::findAll)
                .stream()
                .map(employeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeFullInfo(int id) {
        logger.info("Was invoked method for getting full info employee");
        return employeeRepository.findById(id)
                .map(employeeDTO::fromEmployee)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<EmployeeDTO> getEmployeesFromPage(int page) {
        logger.info("Was invoked method for getting employee with paging");
        return employeeRepository.findAll(PageRequest.of(page, 10))
                .stream()
                .map(employeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile file) {
        logger.info("Was invoked method for loading file");
        if(file!=null){
            ObjectMapper objectMapper = new ObjectMapper();

            List<EmployeeDTO> employeeDTOList =
                    objectMapper.readValue(file.getInputStream(), new TypeReference<List<EmployeeDTO>>(){});

            employeeRepository.saveAll(
                    employeeDTOList.stream().map(EmployeeDTO::toEmployee).toList());
            logger.debug("Database was accessed successfully");
        }else{
            logger.debug("file not found");
            System.out.println("Файл не найден");
        }

    }
}