package com.vromanyu.departmentms.exception;

import com.vromanyu.departmentms.dto.BadRequestDto;
import com.vromanyu.departmentms.dto.InternalErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NonNull BadRequestDto> handleValidationException(@NonNull MethodArgumentNotValidException e, HttpServletRequest request) {
        BadRequestDto badRequestDto = new BadRequestDto(
                request.getRequestURI(),
                e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(ZoneOffset.UTC)
        );
        return new ResponseEntity<>(badRequestDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<@NonNull InternalErrorDto> handleException(Exception e, HttpServletRequest request) {
        InternalErrorDto internalErrorDto = new InternalErrorDto(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(ZoneOffset.UTC)
        );
        return new ResponseEntity<>(internalErrorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
