package com.portfolio.portfolio_backend.service;

import com.portfolio.portfolio_backend.dto.ContactRequestDTO;
import com.portfolio.portfolio_backend.dto.ContactResponseDTO;
import com.portfolio.portfolio_backend.exception.ResourceNotFoundException;
import com.portfolio.portfolio_backend.model.ContactMessage;
import com.portfolio.portfolio_backend.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactResponseDTO saveMessage(ContactRequestDTO dto) {
        log.info("Saving contact message from: {}", dto.getEmail());

        ContactMessage message = new ContactMessage();
        message.setName(dto.getName());
        message.setEmail(dto.getEmail());
        message.setSubject(dto.getSubject());
        message.setMessage(dto.getMessage());

        ContactMessage saved = contactRepository.save(message);
        log.info("Contact message saved with id: {}", saved.getId());
        return mapToResponseDTO(saved);
    }

    public List<ContactResponseDTO> getAllMessages() {
        log.info("Fetching all contact messages");
        return contactRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public ContactResponseDTO getMessageById(Long id) {
        ContactMessage message = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contact message not found with id: " + id));
        return mapToResponseDTO(message);
    }

    public void deleteMessage(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Contact message not found with id: " + id);
        }
        contactRepository.deleteById(id);
        log.info("Deleted contact message with id: {}", id);
    }

    private ContactResponseDTO mapToResponseDTO(ContactMessage msg) {
        return new ContactResponseDTO(
                msg.getId(),
                msg.getName(),
                msg.getEmail(),
                msg.getSubject(),
                msg.getMessage(),
                msg.getCreatedAt()
        );
    }
}