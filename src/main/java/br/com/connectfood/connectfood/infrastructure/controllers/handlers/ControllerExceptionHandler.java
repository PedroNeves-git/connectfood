package br.com.connectfood.connectfood.infrastructure.controllers.handlers;

import br.com.connectfood.connectfood.application.dto.ErrorResponseDTO;
import br.com.connectfood.connectfood.application.dto.ResourceNotFoundDTO;
import br.com.connectfood.connectfood.application.dto.ValidationErrorDTO;
import br.com.connectfood.connectfood.domain.services.exceptions.BadRequestException;
import br.com.connectfood.connectfood.domain.services.exceptions.ConflictException;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import br.com.connectfood.connectfood.domain.services.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException e){
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequest(BadRequestException e) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorResponseDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDTO> handleConflict(ConflictException e) {
        var status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(new ErrorResponseDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnauthorized(UnauthorizedException e) {
        var status = HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(new ErrorResponseDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<String>();
        for(var error: e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(new ValidationErrorDTO(errors, status.value()));
    }



}
