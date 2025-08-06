package br.com.connectfood.connectfood.infrastructure.controllers.handlers;

import br.com.connectfood.connectfood.application.dto.ErrorResponseDTO;
import br.com.connectfood.connectfood.application.dto.ResourceNotFoundDTO;
import br.com.connectfood.connectfood.domain.services.exceptions.BadRequestException;
import br.com.connectfood.connectfood.domain.services.exceptions.ConflictException;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import br.com.connectfood.connectfood.domain.services.exceptions.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class ControllerExceptionHandlerTest {

    private final ControllerExceptionHandler handler = new ControllerExceptionHandler();

    @Test
    void handleBadRequest_deveRetornar400() {
        var ex = new BadRequestException("ruim");
        ResponseEntity<ErrorResponseDTO> response = handler.handleBadRequest(ex);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().message()).isEqualTo("ruim");
    }

    @Test
    void handleConflict_deveRetornar409() {
        var ex = new ConflictException("conflito");
        ResponseEntity<ErrorResponseDTO> response = handler.handleConflict(ex);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody().message()).isEqualTo("conflito");
    }

    @Test
    void handleUnauthorized_deveRetornar401() {
        var ex = new UnauthorizedException("não autorizado");
        ResponseEntity<ErrorResponseDTO> response = handler.handleUnauthorized(ex);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().message()).isEqualTo("não autorizado");
    }

    // Para o MethodArgumentNotValidException, o ideal é testar via integração, mas pode-se mockar se quiser cobertura máxima.
}
