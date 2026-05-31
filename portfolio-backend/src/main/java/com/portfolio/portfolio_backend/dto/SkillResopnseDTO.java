package com.portfolio.portfolio_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillResopnseDTO {
    private Long id;
    private String name;
    private String category;
    private int proficiency;
    private String iconUrl;
}
