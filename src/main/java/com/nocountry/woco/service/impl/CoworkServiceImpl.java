package com.nocountry.woco.service.impl;

import com.nocountry.woco.mapper.CoworkMapper;
import com.nocountry.woco.model.dto.CoworkDto;
import com.nocountry.woco.model.entity.Cowork;
import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.model.repository.CoworkRepository;
import com.nocountry.woco.service.CoworkService;
import com.nocountry.woco.service.spec.CoworkSpecification;
import com.nocountry.woco.utils.enums.EnumService;
import jakarta.persistence.criteria.Expression;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CoworkServiceImpl implements CoworkService {

    private final CoworkRepository coworkRepository;

    private  CoworkMapper coworkMapper;

    private ModelMapper modelMapper;

    public CoworkServiceImpl(CoworkRepository coworkRepository, ModelMapper modelMapper) {
        this.coworkRepository = coworkRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CoworkDto> getAll()
    {
        List<Cowork> coworks = coworkRepository.findAll();
        return coworks.stream().map(cowork -> modelMapper.map(cowork, CoworkDto.class)).collect(Collectors.toList());
    }

    @Override
    public CoworkDto getById(long id) throws ResourceNotFoundException {
        Cowork cowork = coworkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("cowork with id " + id + " not found"));
        return coworkMapper.toCoworkDTO(cowork);
    }

    @Override
    public CoworkDto post(Cowork cowork) {

        Cowork savedCowork = coworkRepository.save(cowork);
        return coworkMapper.toCoworkDTO(savedCowork);
    }

    @Override
    public CoworkDto patch(long id, Cowork cowork) throws ResourceNotFoundException {
        Cowork existingCowork = coworkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cowork with id " + id + " not found"));
        if (cowork.getName() != null) {
            existingCowork.setName(cowork.getName());
        }
        existingCowork.setDescription(cowork.getDescription());
        existingCowork.setCapacity(cowork.getCapacity());
        existingCowork.setLatitude(cowork.getLatitude());
        existingCowork.setLongitude(cowork.getLongitude());
        existingCowork.setPricePerDay(cowork.getPricePerDay());
        existingCowork.setAddress(cowork.getAddress());
        existingCowork.setRatings(cowork.getRatings());
        existingCowork.setZipCode(cowork.getZipCode());
        if (cowork.getPhotos() != null) {
            existingCowork.setPhotos(cowork.getPhotos());
        }

        if (cowork.getLocation() != null) {
            existingCowork.setLocation(cowork.getLocation());
        }
        if (cowork.getServices()!= null) {
            existingCowork.setServices(cowork.getServices());
        }
        if (cowork.getContact() != null) {
            existingCowork.setContact(cowork.getContact());
        }
        Cowork updatedCowork = coworkRepository.save(existingCowork);
        return coworkMapper.toCoworkDTO(updatedCowork);
    }

    @Override
    public CoworkDto delete(long id) throws ResourceNotFoundException {
        Cowork coworkToDelete = coworkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cowork with id " + id + " not found"));
        coworkRepository.delete(coworkToDelete);
        return coworkMapper.toCoworkDTO(coworkToDelete);
    }

    @Override
    public List<CoworkDto> getByServices(long id) {
        return coworkMapper.toCoworksDTO(coworkRepository.findByServices_id(id));
    }

    @Override
    public List<CoworkDto> getByLocation(long id) {
        return coworkMapper.toCoworksDTO(coworkRepository.findByLocation_id(id));
    }

    @Override
    public List<CoworkDto> getByNameContaining(String title) {
        return coworkMapper.toCoworksDTO(coworkRepository.findByNameContaining(title));
    }

    @Override
    public List<CoworkDto> getByLatitudeLongitudeAndDistance(float latitude, float longitude, float distance) {
        return coworkMapper.toCoworksDTO(coworkRepository.findByLatitudeLongitudeAndDistance(latitude, longitude, distance));
    }

    @Override
    public List<CoworkDto> getByLatitudeLongitudeDistanceAndServiceId(float latitude, float longitude, float distance, long serviceId) {
        return coworkMapper.toCoworksDTO(coworkRepository.findByLatitudeLongitudeDistanceAndService(latitude, longitude, distance, serviceId));
    }

    @Override
    public List<CoworkDto> getByServiceIdAndLocationId(long serviceId, long locationId) {
        return coworkMapper.toCoworksDTO(coworkRepository.findByServicesIdAndCityId(serviceId, locationId));
    }

    @Override
    public List<CoworkDto> findKoworksByFilters(boolean betters, boolean closer, boolean room, Double minPrice, Double maxPrice, boolean sWifi, boolean sPrinter, boolean sChairs, boolean sParking, boolean sLockers, boolean sCalefaction) {
        Set<String> hasServiceSet = new HashSet<>();
        Specification<Cowork> spec = Specification.where(null);

        if(betters) {
            //TODO:cambiar por metodo o crear un atributo calculado para el promedio de rating
            Double maxRating = 4.00;
            spec = spec.and(CoworkSpecification.hasBetterRating(maxRating));
        }

        if(minPrice!= null && maxPrice!= null) {
            spec=spec.and(CoworkSpecification.hasPricesBetween(minPrice, maxPrice));
        }
        if(sWifi) {
            hasServiceSet.add(EnumService.WIFI.toString());
        }
        if(sPrinter) {
            hasServiceSet.add(EnumService.PRINTER.toString());
        }
        if(sChairs) {
            hasServiceSet.add(EnumService.CHAIRS.toString());
        }
        if(sParking) {
            hasServiceSet.add(EnumService.PARKING.toString());
        }
        if(sLockers) {
            hasServiceSet.add(EnumService.LOCKERS.toString());
        }
        if(sCalefaction) {
            hasServiceSet.add(EnumService.CALEFACTION.toString());
        }
        //TODO: hacer el spec cuando compare con el hashSet

        if(!hasServiceSet.isEmpty()){
            spec= spec.and( (root, query, criteriaBuilder) -> {
                Expression<String> serviceExpression = root.get("services").get("name");
                return serviceExpression.in(hasServiceSet);
            });
        }

        List<Cowork> coworks = coworkRepository.findAll(spec);
        if (coworks.isEmpty()) {
            return Collections.emptyList();
        }
        return coworks.stream().map(cowork ->modelMapper.map(cowork, CoworkDto.class)).collect(Collectors.toList());
    }

}
