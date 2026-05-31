package com.portfolio.portfolio_backend.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProjectRepository {
    List<Project> findByFeaturedTrue();
}
