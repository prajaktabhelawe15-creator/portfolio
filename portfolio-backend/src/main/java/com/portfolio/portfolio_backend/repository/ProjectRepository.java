package com.portfolio.portfolio_backend.repository;

import com.portfolio.portfolio_backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByFeaturedTrue();

    List<Project> findByTechStackContainingIgnoreCase(String keyword);
}