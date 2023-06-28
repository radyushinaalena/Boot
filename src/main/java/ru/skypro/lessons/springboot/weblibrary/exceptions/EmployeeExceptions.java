package ru.skypro.lessons.springboot.weblibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class EmployeeExceptions {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeExceptions.class);

    @ExceptionHandler
    public ResponseEntity<?> handleIOException(IOException ioException) {
        logger.error(ioException.getMessage(), ioException);
        String message = "Неверно передан id работника";
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleSQLException(SQLException sqlException) {
        logger.error(sqlException.getMessage(), sqlException);
        String message = "В ходе работы приложения произошла ошибка на сервере";
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
