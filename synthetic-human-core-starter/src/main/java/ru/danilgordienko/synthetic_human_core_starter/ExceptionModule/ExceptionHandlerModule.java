package ru.danilgordienko.synthetic_human_core_starter.ExceptionModule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

//@ControllerAdvice
@Slf4j
public class ExceptionHandlerModule {

    // обработка ошибки при переполнии очереди задач
    @ExceptionHandler(RejectedExecutionException.class)
    public ResponseEntity<String> handleRejectedExecution(RejectedExecutionException ex) {
        log.error("Command rejected: queue is full", ex);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Command queue is full");
    }

    // обработка ошибки валидации
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationError(MethodArgumentNotValidException ex) {
        // собираем ошибки
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest()
                .body("Validation error: " + message);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {
        log.error("Unexpected error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal android error occurred");
    }


}
