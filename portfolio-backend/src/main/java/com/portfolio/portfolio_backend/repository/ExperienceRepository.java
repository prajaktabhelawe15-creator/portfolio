package com.portfolio.portfolio_backend.repository;

import com.portfolio.portfolio_backend.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    List<Experience> findAllByOrderByCurrentDescStartDateDesc();

    List<Experience> findByCurrentTrue();
}