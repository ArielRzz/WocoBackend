package com.nocountry.woco.controller;

import com.nocountry.woco.model.dto.CoworkDto;
import com.nocountry.woco.model.entity.Cowork;
import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.service.CoworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/coworks")
@RequiredArgsConstructor
public class CoworkController {

    private final CoworkService coworkService;

        @GetMapping()
        public ResponseEntity<List<CoworkDto>> getAll() {
            try {
                List<CoworkDto> response = coworkService.getAll();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<CoworkDto> getById(@PathVariable int id) throws ResourceNotFoundException {
            CoworkDto response = coworkService.getById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @PostMapping("/")
        public ResponseEntity<CoworkDto> post(@RequestBody Cowork cowork) {
            CoworkDto createdCowork = coworkService.post(cowork);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCowork);
        }

        @PatchMapping("/{id}")
        public ResponseEntity<CoworkDto> patch(@PathVariable int id, @RequestBody Cowork cowork) throws ResourceNotFoundException {
            CoworkDto updatedCowork = coworkService.patch(id, cowork);
            return ResponseEntity.ok(updatedCowork);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete(@PathVariable int id) throws ResourceNotFoundException {
            coworkService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("Cowork deleted");
        }

        @GetMapping("/locations/{id}")
        public ResponseEntity<List<CoworkDto>> getByLocation(@PathVariable int id) throws ResourceNotFoundException {
            try {
                List<CoworkDto> response = coworkService.getByLocation(id);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @GetMapping("/services/{id}")
        public ResponseEntity<List<CoworkDto>> getByService(@PathVariable int id) throws ResourceNotFoundException {
            try {
                List<CoworkDto> response = coworkService.getByServices(id);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @GetMapping("/name")
        public ResponseEntity<List<CoworkDto>> findByNameContaining(@RequestParam String name) {
            try {
                List<CoworkDto> response = coworkService.getByNameContaining(name);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @GetMapping("/find")
        public ResponseEntity<List<CoworkDto>> findByLatitudeLongitudeAndDistance(@RequestParam float latitude, @RequestParam float longitude, @RequestParam float distance) {
            try {
                List<CoworkDto> response = coworkService.getByLatitudeLongitudeAndDistance(latitude, longitude, distance);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @GetMapping("/find/service")
        public ResponseEntity<List<CoworkDto>> findByLatitudeLongitudeDistanceAndCategory(@RequestParam float latitude, @RequestParam float longitude, @RequestParam float distance, @RequestParam int serviceId) {
            try {
                List<CoworkDto> response = coworkService.getByLatitudeLongitudeDistanceAndServiceId(latitude, longitude, distance, serviceId);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @GetMapping("/find/city")
        public ResponseEntity<List<CoworkDto>> findByCategoryIdAndCityId(@RequestParam int serviceId, @RequestParam int locationId) {
            try {
                List<CoworkDto> response = coworkService.getByServiceIdAndLocationId(serviceId, locationId);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

    @GetMapping("/filters")
    public ResponseEntity<List<CoworkDto>> getCoworksByFilters(
            @RequestParam(required = false) boolean betters,
            @RequestParam(required = false) boolean closer,
            @RequestParam(required = false) boolean room,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) boolean sWifi,
            @RequestParam(required = false) boolean sPrinter,
            @RequestParam(required = false) boolean sChairs,
            @RequestParam(required = false) boolean sParking,
            @RequestParam(required = false) boolean sLockers,
            @RequestParam(required = false) boolean sCalefaction)
    {
        List<CoworkDto> coworks= coworkService.findKoworksByFilters(
                betters,
                closer,
                room,
                minPrice,
                maxPrice,
                sWifi,
                sPrinter,
                sChairs,
                sParking,
                sLockers,
                sCalefaction
        );
        if (coworks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(coworks, HttpStatus.OK);
        }
    }



    }

