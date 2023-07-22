package com.nocountry.woco.controller;

import com.nocountry.woco.model.dto.ContactDTO;
import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping()
    public ResponseEntity<List<ContactDTO>> getAll() {
        try {
            List<ContactDTO> response = contactService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getById(@PathVariable int id) throws ResourceNotFoundException {
        ContactDTO response = contactService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

