package com.nocountry.woco.mapper;

import com.nocountry.woco.model.dto.CoworkDto;
import com.nocountry.woco.model.entity.Cowork;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
//AGREGAR PhotosMapper.class, ServicesMapper.class,

@Mapper(componentModel = "spring", uses = { LocationMapper.class, ContactMapper.class,})
public interface CoworkMapper {
    CoworkMapper INSTANCE = Mappers.getMapper(CoworkMapper.class);
    CoworkDto toCoworkDTO(Cowork cowork);

    Cowork toCowork(CoworkDto DTO);

    List<CoworkDto> toCoworksDTO(List<Cowork> coworks);

    List<Cowork> toCoworks(List<CoworkDto> CoworksDTO);
}
