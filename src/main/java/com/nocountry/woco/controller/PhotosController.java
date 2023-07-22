package com.nocountry.woco.controller;

import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.model.request.PhotosRequest;
import com.nocountry.woco.model.response.PhotosResponse;
import com.nocountry.woco.service.PhotosService;
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

    @PostMapping("")
    public ResponseEntity<PhotosResponse> addPhoto(@Valid @RequestBody PhotosRequest photosRequest) throws ResourceNotFoundException {
       return new ResponseEntity<PhotosResponse>(photosService.addPhoto(photosRequest),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PhotosResponse> updatePhoto(@PathVariable int id,@Valid @RequestBody PhotosRequest photosRequest) throws ResourceNotFoundException {
        return new ResponseEntity<PhotosResponse>(photosService.updatePhoto(Long.valueOf(id),
                photosRequest),HttpStatus.OK);
    }

}
