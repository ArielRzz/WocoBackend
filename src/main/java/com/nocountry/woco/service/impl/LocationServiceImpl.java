package com.nocountry.woco.service.impl;

import com.nocountry.woco.mapper.LocationMapper;
import com.nocountry.woco.model.dto.LocationDto;
import com.nocountry.woco.model.entity.Location;
import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.model.repository.LocationRepository;
import com.nocountry.woco.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class  LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private  LocationMapper locationMapper;


    @Override
    public List<LocationDto> getAll() {
        return locationMapper.toLocationsDTO(locationRepository.findAll());
    }

    @Override
    public LocationDto getById(long id) throws ResourceNotFoundException {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with id " + id + " not found"));
        return locationMapper.toLocationDTO(location);
    }


    @Override
    public LocationDto post(Location location) {
        Location savedLocation = locationRepository.save(location);
        return locationMapper.toLocationDTO(savedLocation);
    }

    @Override
    public LocationDto patch(long id, Location location) throws ResourceNotFoundException {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with id " + id + " not found"));
        if (location.getCity() != null) {
            existingLocation.setCity(location.getCity());
        }
        if (location.getProvince() != null) {
            existingLocation.setProvince(location.getProvince());
        }
        if (location.getCountry() != null) {
            existingLocation.setCountry(location.getCountry());
        }
        Location updatedLocation = locationRepository.save(existingLocation);
        return locationMapper.toLocationDTO(updatedLocation);
    }

    @Override
    public LocationDto delete(long id) throws ResourceNotFoundException {
        Location locationToDelete = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with id " + id + " not found"));
        locationRepository.delete(locationToDelete);
        return locationMapper.toLocationDTO(locationToDelete);
    }

    @Override
    public List<LocationDto> getBySearch(String search) {
        List<Location> locations = locationRepository.findAllByCityOrProvinceOrCountryContaining(search);
        return locations.stream()
                .map(location -> locationMapper.toLocationDTO(location))
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationDto> getByCityContaining(String city) {
        List<Location> locations = locationRepository.findByCityContaining(city);
        return locations.stream()
                .map(c -> locationMapper.toLocationDTO(c))
                .collect(Collectors.toList());

    }


}