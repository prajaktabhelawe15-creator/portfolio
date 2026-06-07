package com.portfolio.portfolio_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {

    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors;
}