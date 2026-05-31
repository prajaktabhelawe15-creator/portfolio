package com.portfolio.portfolio_backend.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ProjectRequestDTO {

    @NotBlank(message = "Project title is required")
    @Size(min = 2, max = 150, message = "Title must be between 2 and 150 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Tech stack is required")
    private String techStack;

    private String githubUrl;
    private String liveUrl;
    private String imageUrl;

    private boolean featured = false;
}