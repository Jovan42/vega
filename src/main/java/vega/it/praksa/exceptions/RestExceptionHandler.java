package vega.it.praksa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vega.it.praksa.model.dtos.ErrorMessages;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessages> processBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessages> processNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getErrorMessages(), HttpStatus.NOT_FOUND);
    }
}
