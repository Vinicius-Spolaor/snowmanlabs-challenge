package com.snowmanlabs.challenge.exception.advice;

import com.snowmanlabs.challenge.dto.ErrorDto;
import com.snowmanlabs.challenge.exception.BusinessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ResponseEntityAdvice {

    @ExceptionHandler({ BusinessException.class })
    protected ResponseEntity<?> handleBusinessException(BusinessException ex) {
        var errorDTO = new ErrorDto();
        errorDTO.setCode(HttpStatus.BAD_REQUEST.value());
        errorDTO.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorDTO.setReason(ex.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errorMessage = Objects.isNull(ex.getFieldError())
                         ? ex.getMessage()
                         : String.format("Field %s %s", ex.getFieldError().getField(), ex.getFieldError().getDefaultMessage());

        var errorDTO = new ErrorDto();

        errorDTO.setCode(HttpStatus.BAD_REQUEST.value());
        errorDTO.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorDTO.setReason(errorMessage);
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    protected ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        var errorDTO = new ErrorDto();

        errorDTO.setCode(HttpStatus.BAD_REQUEST.value());
        errorDTO.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorDTO.setReason("Data integrity violation.");
        return ResponseEntity.badRequest().body(errorDTO);
    }

}
