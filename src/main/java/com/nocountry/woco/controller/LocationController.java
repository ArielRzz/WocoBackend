package com.nocountry.woco.controller;

import com.nocountry.woco.model.dto.LocationDto;
import com.nocountry.woco.model.entity.Location;
import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.service.LocationService;
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

    @GetMapping()
    public ResponseEntity<List<LocationDto>> getAll() {
        try {
            List<LocationDto> response = locationService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getById(@PathVariable int id) throws ResourceNotFoundException {
        LocationDto response = locationService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<LocationDto> postCity(@RequestBody Location location) {
        LocationDto createdLocation = locationService.post(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LocationDto> patchCity(@PathVariable int id, @RequestBody Location location) throws ResourceNotFoundException {
        LocationDto updatedCity = locationService.patch(id, location);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable int id) throws ResourceNotFoundException {
        locationService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("City deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<List<LocationDto>> getBySearch(@RequestParam String search) {
        List<LocationDto> cities = locationService.getBySearch(search);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/name")
    public ResponseEntity<List<LocationDto>> getCityByCity(@RequestParam String city) {
        List<LocationDto> cities = locationService.getByCityContaining(city);
        return ResponseEntity.ok(cities);
    }


}

