package com.nocountry.woco.service;

import com.nocountry.woco.model.dto.LocationDto;
import com.nocountry.woco.model.entity.Location;
import com.nocountry.woco.model.exception.ResourceNotFoundException;

import java.util.List;

public interface LocationService {


    List<LocationDto> getAll();
    LocationDto getById(long id) throws ResourceNotFoundException;


    LocationDto post(Location location);

    LocationDto patch(long id, Location location) throws ResourceNotFoundException;

    LocationDto delete(long id) throws ResourceNotFoundException;


    List<LocationDto> getBySearch(String search);
    List<LocationDto> getByCityContaining(String city);
}

