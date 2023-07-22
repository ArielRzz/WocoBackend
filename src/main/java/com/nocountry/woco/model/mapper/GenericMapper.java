package com.nocountry.woco.model.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenericMapper {

    private final ModelMapper mapper;
    private final ObjectMapper jsonMapper;


    public <S, D> D jsonMap(String json, Class<D> destinationClass)
            throws JsonMappingException, JsonProcessingException {
        return jsonMapper.readValue(json, destinationClass);
    }

    public <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    public <S, D> List<D> mapAll(List<S> source, Class<D> destinationClass) {
        return source.stream()
                .map(e -> map(e, destinationClass))
                .collect(Collectors.toList());
    }

}
