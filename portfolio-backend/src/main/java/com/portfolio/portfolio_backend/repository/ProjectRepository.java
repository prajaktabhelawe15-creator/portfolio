package com.portfolio.portfolio_backend.repository;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {
    List<Project> findByFeaturedTrue();
}
