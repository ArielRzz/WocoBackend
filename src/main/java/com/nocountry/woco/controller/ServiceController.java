package com.nocountry.woco.controller;

import com.nocountry.woco.model.entity.Services;
import com.nocountry.woco.model.request.ServiceRequest;
import com.nocountry.woco.model.response.ServiceResponse;
import com.nocountry.woco.service.IServiceService;
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
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final IServiceService serviceService;

    @Operation(summary = "Get all services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the service",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Services.class)) }),
            @ApiResponse(responseCode = "404", description = "Service not found",
                    content = @Content) })
    @GetMapping
    public List<ServiceResponse> getAllServices() {
        return serviceService.getAllServices();
    }

    @Operation(summary = "Get a service by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the service",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Services.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Service not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ServiceResponse getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    @Operation(summary = "Add a new service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the service",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Services.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)
    })
    @PostMapping
    public ServiceResponse addService(@RequestBody ServiceRequest serviceRequest) {
        return serviceService.addService(serviceRequest);
    }

    @Operation(summary = "Update an existing service")
    @ApiResponses( value = { @ApiResponse(responseCode = "200", description = "Updated the"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Service not found",
                    content = @Content)
    })
    @PutMapping
    public ServiceResponse updateService(@RequestParam Long id,@RequestBody ServiceRequest serviceRequest) {
        return serviceService.updateService(id,serviceRequest);
    }

    @Operation(summary = "Delete a service by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the service",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "Service deleted",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            serviceService.deleteService(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
