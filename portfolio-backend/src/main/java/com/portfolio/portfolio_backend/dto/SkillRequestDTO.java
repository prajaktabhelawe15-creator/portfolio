package com.portfolio.portfolio_backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SkillRequestDTO {

    @NotBlank(message = "Skill name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 0, message = "Proficiency cannot be less than 0")
    @Max(value = 100, message = "Proficiency cannot be more than 100")
    private int proficiency;

    private String iconUrl;
}