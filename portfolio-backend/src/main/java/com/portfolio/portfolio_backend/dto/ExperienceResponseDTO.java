package com.portfolio.portfolio_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceResponseDTO {

    private Long id;
    private String company;
    private String role;
    private String description;
    private String startDate;
    private String endDate;
    private boolean current;
    private String location;
}