package com.nocountry.woco.controller;

import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.model.request.RatingRequest;
import com.nocountry.woco.model.response.RatingResponse;
import com.nocountry.woco.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping()
    public ResponseEntity<RatingResponse> addRating(@Valid @RequestBody RatingRequest ratingRequest) throws ResourceNotFoundException {
        return new ResponseEntity<RatingResponse>(ratingService.addRating(ratingRequest), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RatingResponse> updateRating(@PathVariable int id,@Valid @RequestBody RatingRequest ratingRequest) throws ResourceNotFoundException {
        return new ResponseEntity<RatingResponse>(ratingService.updateRating(Long.valueOf(id),
                ratingRequest),HttpStatus.OK);
    }
}
