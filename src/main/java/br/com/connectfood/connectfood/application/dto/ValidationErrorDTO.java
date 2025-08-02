package br.com.connectfood.connectfood.application.dto;

import java.util.List;

public record ValidationErrorDTO(
        List<String> errors,
        int status
) {
}
