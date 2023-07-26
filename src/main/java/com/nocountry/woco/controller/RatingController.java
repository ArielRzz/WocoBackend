package com.nocountry.woco.controller;

import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.model.request.RatingRequest;
import com.nocountry.woco.model.response.RatingResponse;
import com.nocountry.woco.service.RatingService;
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
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @Operation( summary = "Add a new rating", description = "Adds a new rating to the database" )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rating added successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "404", description = "Resource not found")
            }
    )
    @PostMapping()
    public ResponseEntity<RatingResponse> addRating(@Valid @RequestBody RatingRequest ratingRequest) throws ResourceNotFoundException {
        return new ResponseEntity<RatingResponse>(ratingService.addRating(ratingRequest), HttpStatus.OK);
    }
    @Operation( summary = "Update an existing rating", description = "Updates an existing rating in the database" )
    @PutMapping("/{id}")
    public ResponseEntity<RatingResponse> updateRating(@PathVariable int id,@Valid @RequestBody RatingRequest ratingRequest) throws ResourceNotFoundException {
        return new ResponseEntity<RatingResponse>(ratingService.updateRating(Long.valueOf(id),
                ratingRequest),HttpStatus.OK);
    }
}
