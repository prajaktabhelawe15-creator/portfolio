package com.portfolio.portfolio_backend.service;

import com.portfolio.portfolio_backend.dto.SkillRequestDTO;
import com.portfolio.portfolio_backend.dto.SkillResponseDTO;
import com.portfolio.portfolio_backend.exception.ResourceNotFoundException;
import com.portfolio.portfolio_backend.model.Skill;
import com.portfolio.portfolio_backend.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillResponseDTO saveSkill(SkillRequestDTO dto) {
        log.info("Saving new skill: {}", dto.getName());

        Skill skill = new Skill();
        skill.setName(dto.getName());
        skill.setCategory(dto.getCategory());
        skill.setProficiency(dto.getProficiency());
        skill.setIconUrl(dto.getIconUrl());

        Skill saved = skillRepository.save(skill);
        return mapToResponseDTO(saved);
    }

    public List<SkillResponseDTO> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<SkillResponseDTO> getSkillsByCategory(String category) {
        return skillRepository.findByCategoryOrderByProficiencyDesc(category)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public SkillResponseDTO updateSkill(Long id, SkillRequestDTO dto) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Skill not found with id: " + id));

        skill.setName(dto.getName());
        skill.setCategory(dto.getCategory());
        skill.setProficiency(dto.getProficiency());
        skill.setIconUrl(dto.getIconUrl());

        return mapToResponseDTO(skillRepository.save(skill));
    }

    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
        log.info("Deleted skill with id: {}", id);
    }

    private SkillResponseDTO mapToResponseDTO(Skill skill) {
        return new SkillResponseDTO(
                skill.getId(),
                skill.getName(),
                skill.getCategory(),
                skill.getProficiency(),
                skill.getIconUrl()
        );
    }
}