package com.portfolio.portfolio_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String techStack;
    private String githubUrl;
    private String liveUrl;
    private String imageUrl;
    private boolean featured;
    private LocalDateTime createdAt;
}