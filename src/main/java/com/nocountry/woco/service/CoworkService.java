package com.nocountry.woco.service;

import com.nocountry.woco.model.dto.CoworkDto;
import com.nocountry.woco.model.entity.Cowork;
import com.nocountry.woco.model.exception.ResourceNotFoundException;

import java.util.List;

public interface CoworkService {
    List<CoworkDto> getAll();
    CoworkDto getById(long id) throws ResourceNotFoundException;

    CoworkDto post(Cowork cowork);

    CoworkDto patch(long id, Cowork cowork) throws ResourceNotFoundException;

    CoworkDto delete(long id) throws ResourceNotFoundException;

    List<CoworkDto> getByServices(long id);
    List<CoworkDto> getByLocation(long id);
    List<CoworkDto> getByNameContaining(String name);
    List<CoworkDto> getByLatitudeLongitudeAndDistance( float latitude,float longitude,  float distance);

    List<CoworkDto> getByLatitudeLongitudeDistanceAndServiceId( float latitude,float longitude,float distance, long serviceId);

    List<CoworkDto> getByServiceIdAndLocationId(long ServiceId, long locationId);

    List<CoworkDto> findKoworksByFilters(boolean betters, boolean closer, boolean room, Double minPrice, Double maxPrice, boolean sWifi, boolean sPrinter, boolean sChairs, boolean sParking, boolean sLockers, boolean sCalefaction);
}
