package com.portfolio.portfolio_backend.service;

import com.portfolio.portfolio_backend.dto.ExperienceRequestDTO;
import com.portfolio.portfolio_backend.dto.ExperienceResponseDTO;
import com.portfolio.portfolio_backend.exception.ResourceNotFoundException;
import com.portfolio.portfolio_backend.model.Experience;
import com.portfolio.portfolio_backend.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceResponseDTO saveExperience(ExperienceRequestDTO dto) {
        log.info("Saving experience at: {}", dto.getCompany());

        Experience experience = new Experience();
        experience.setCompany(dto.getCompany());
        experience.setRole(dto.getRole());
        experience.setDescription(dto.getDescription());
        experience.setStartDate(dto.getStartDate());
        experience.setEndDate(dto.getEndDate());
        experience.setCurrent(dto.isCurrent());
        experience.setLocation(dto.getLocation());

        Experience saved = experienceRepository.save(experience);
        return mapToResponseDTO(saved);
    }

    public List<ExperienceResponseDTO> getAllExperiences() {
        return experienceRepository
                .findAllByOrderByCurrentDescStartDateDesc()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public ExperienceResponseDTO getExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Experience not found with id: " + id));
        return mapToResponseDTO(experience);
    }

    public ExperienceResponseDTO updateExperience(Long id, ExperienceRequestDTO dto) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Experience not found with id: " + id));

        experience.setCompany(dto.getCompany());
        experience.setRole(dto.getRole());
        experience.setDescription(dto.getDescription());
        experience.setStartDate(dto.getStartDate());
        experience.setEndDate(dto.getEndDate());
        experience.setCurrent(dto.isCurrent());
        experience.setLocation(dto.getLocation());

        return mapToResponseDTO(experienceRepository.save(experience));
    }

    public void deleteExperience(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Experience not found with id: " + id);
        }
        experienceRepository.deleteById(id);
        log.info("Deleted experience with id: {}", id);
    }

    private ExperienceResponseDTO mapToResponseDTO(Experience experience) {
        return new ExperienceResponseDTO(
                experience.getId(),
                experience.getCompany(),
                experience.getRole(),
                experience.getDescription(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.isCurrent(),
                experience.getLocation()
        );
    }
}