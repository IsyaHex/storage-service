package uz.epam.msa.storageservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.epam.msa.storageservice.dto.ErrorDto;
import uz.epam.msa.storageservice.exception.InternalServerError;
import uz.epam.msa.storageservice.exception.StorageValidationException;

@RestControllerAdvice
public class ExceptionProcessor extends ResponseEntityExceptionHandler {
    private static final int INCORRECT_PARAMETER_VALUE_CODE = 400;
    private static final int INTERNAL_SERVER_ERROR = 500;


    @ExceptionHandler(value = StorageValidationException.class)
    public ResponseEntity<ErrorDto> handleIncorrectParameterValueException(StorageValidationException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage(), INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InternalServerError.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(InternalServerError exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage(), INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
