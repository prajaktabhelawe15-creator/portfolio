package com.portfolio.portfolio_backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ExperienceRequestDTO {

    @NotBlank(message = "Company name is required")
    private String company;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Start date is required")
    private String startDate;

    private String endDate;

    private boolean current = false;

    private String location;
}