package com.nocountry.woco.controller;

import com.nocountry.woco.model.dto.LocationDto;
import com.nocountry.woco.model.entity.Location;
import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @Operation( summary = "Get all locations", description= "Get all locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping()
    public ResponseEntity<List<LocationDto>> getAll() {
        try {
            List<LocationDto> response = locationService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation( summary = "Get location by id", description= "Get location by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getById(@PathVariable int id) throws ResourceNotFoundException {
        LocationDto response = locationService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation( summary = "Create location", description= "Create location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/")
    public ResponseEntity<LocationDto> postCity(@RequestBody Location location) {
        LocationDto createdLocation = locationService.post(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }

    @Operation( summary = "Update location", description= "Update location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<LocationDto> patchCity(@PathVariable int id, @RequestBody Location location) throws ResourceNotFoundException {
        LocationDto updatedCity = locationService.patch(id, location);
        return ResponseEntity.ok(updatedCity);
    }

    @Operation( summary = "Delete location", description= "Delete location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Acepted"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable int id) throws ResourceNotFoundException {
        locationService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("City deleted");
    }

    @Operation( summary = "Get location by search", description= "Get location by search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/search")
    public ResponseEntity<List<LocationDto>> getBySearch(@RequestParam String search) {
        List<LocationDto> cities = locationService.getBySearch(search);
        return ResponseEntity.ok(cities);
    }

    @Operation( summary = "Get location by city", description= "Get location by city")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode =  "404", description = "Not Found"),
    })
    @GetMapping("/name")
    public ResponseEntity<List<LocationDto>> getCityByCity(@RequestParam(required = false) String city) {
        if (city == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        List<LocationDto> cities = locationService.getByCityContaining(city);
        if (cities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cities);
    }

}

