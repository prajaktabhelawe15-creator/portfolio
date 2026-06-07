package com.portfolio.portfolio_backend.service;

import com.portfolio.portfolio_backend.dto.ProjectRequestDTO;
import com.portfolio.portfolio_backend.dto.ProjectResponseDTO;
import com.portfolio.portfolio_backend.exception.ResourceNotFoundException;
import com.portfolio.portfolio_backend.model.Project;
import com.portfolio.portfolio_backend.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectResponseDTO saveProject(ProjectRequestDTO dto) {
        log.info("Saving new project: {}", dto.getTitle());

        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setTechStack(dto.getTechStack());
        project.setGithubUrl(dto.getGithubUrl());
        project.setLiveUrl(dto.getLiveUrl());
        project.setImageUrl(dto.getImageUrl());
        project.setFeatured(dto.isFeatured());

        Project saved = projectRepository.save(project);
        log.info("Project saved with id: {}", saved.getId());
        return mapToResponseDTO(saved);
    }

    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectResponseDTO> getFeaturedProjects() {
        return projectRepository.findByFeaturedTrue()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public ProjectResponseDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with id: " + id));
        return mapToResponseDTO(project);
    }

    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with id: " + id));

        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setTechStack(dto.getTechStack());
        project.setGithubUrl(dto.getGithubUrl());
        project.setLiveUrl(dto.getLiveUrl());
        project.setImageUrl(dto.getImageUrl());
        project.setFeatured(dto.isFeatured());

        Project updated = projectRepository.save(project);
        log.info("Project updated with id: {}", updated.getId());
        return mapToResponseDTO(updated);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
        log.info("Deleted project with id: {}", id);
    }

    private ProjectResponseDTO mapToResponseDTO(Project project) {
        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getTechStack(),
                project.getGithubUrl(),
                project.getLiveUrl(),
                project.getImageUrl(),
                project.isFeatured(),
                project.getCreatedAt()
        );
    }
}