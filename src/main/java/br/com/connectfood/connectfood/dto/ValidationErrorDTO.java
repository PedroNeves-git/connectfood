package br.com.connectfood.connectfood.dto;

import java.util.List;

public record ValidationErrorDTO(
        List<String> errors,
        int status
) {
}
