package com.nocountry.woco.controller;

import com.nocountry.woco.model.dto.ContactDTO;
import com.nocountry.woco.model.entity.Services;
import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation( summary = "Get all contacts" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping()
    public ResponseEntity<List<ContactDTO>> getAll() {
        try {
            List<ContactDTO> response = contactService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get a contact by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the contact",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Services.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Contact not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getById(@PathVariable int id) throws ResourceNotFoundException {
        ContactDTO response = contactService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

