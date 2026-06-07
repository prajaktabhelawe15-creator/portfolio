package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.dto.ContactRequestDTO;
import com.portfolio.portfolio_backend.dto.ContactResponseDTO;
import com.portfolio.portfolio_backend.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    // POST /api/contact — save a new message
    @PostMapping
    public ResponseEntity<ContactResponseDTO> saveMessage(
            @Valid @RequestBody ContactRequestDTO dto) {
        ContactResponseDTO response = contactService.saveMessage(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/contact — get all messages
    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getAllMessages() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }

    // GET /api/contact/1 — get single message by id
    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getMessageById(
            @PathVariable Long id) {
        return ResponseEntity.ok(contactService.getMessageById(id));
    }

    // DELETE /api/contact/1 — delete a message
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(
            @PathVariable Long id) {
        contactService.deleteMessage(id);
        return ResponseEntity.ok("Contact message deleted successfully");
    }
}