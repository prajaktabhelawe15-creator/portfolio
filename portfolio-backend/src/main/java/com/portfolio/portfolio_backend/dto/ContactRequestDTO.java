package com.portfolio.portfolio_backend.dto;

import lombok.Data;

@Data
public class ContactRequestDTO {
    private String name;
    private String email;
    private String subject;
    private String message;
}
