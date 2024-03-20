package br.com.grandrade.rentcar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Trata todas as exceções não capturadas explicitamente pelos outros manipuladores
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomExceptionResponse> handleAllExceptions(Exception e, WebRequest request){
        CustomExceptionResponse response = new CustomExceptionResponse(new Date(),
                e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Trata casos específicos onde um recurso não é encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<CustomExceptionResponse> handleResourceNotFoundException(@NonNull Exception e, @NonNull WebRequest request){
        CustomExceptionResponse response = new CustomExceptionResponse(new Date(),
                e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
