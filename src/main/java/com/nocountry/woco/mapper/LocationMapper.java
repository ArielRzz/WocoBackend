package com.nocountry.woco.mapper;

import com.nocountry.woco.model.dto.LocationDto;
import com.nocountry.woco.model.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper( LocationMapper.class );

    LocationDto toLocationDTO(Location Location);
    Location toLocation(LocationDto DTO);
    List<LocationDto> toLocationsDTO(List<Location> locations);
    List<Location> toLocations(List<LocationDto> locationsDTO);

}