package com.nocountry.woco.controller;

import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.model.request.PhotosRequest;
import com.nocountry.woco.model.response.PhotosResponse;
import com.nocountry.woco.service.PhotosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotosController {
    private final PhotosService photosService;

    @Operation( summary = "Add a new photo", description = "Add a new photo to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Photo added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("")
    public ResponseEntity<PhotosResponse> addPhoto(@Valid @RequestBody PhotosRequest photosRequest) throws ResourceNotFoundException {
       return new ResponseEntity<PhotosResponse>(photosService.addPhoto(photosRequest),HttpStatus.OK);
    }

    @Operation( summary = "Update a photo", description = "Update a photo in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Photo updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PhotosResponse> updatePhoto(@PathVariable int id,@Valid @RequestBody PhotosRequest photosRequest) throws ResourceNotFoundException {
        return new ResponseEntity<PhotosResponse>(photosService.updatePhoto(Long.valueOf(id),
                photosRequest),HttpStatus.OK);
    }

}
