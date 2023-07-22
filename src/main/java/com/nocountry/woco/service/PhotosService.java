package com.nocountry.woco.service;

import com.nocountry.woco.model.entity.Photos;
import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.model.repository.PhotosRepository;
import com.nocountry.woco.model.request.PhotosRequest;
import com.nocountry.woco.model.response.PhotosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotosService {
    private final PhotosRepository photosRepository;

    public PhotosResponse addPhoto(PhotosRequest photosRequest) throws ResourceNotFoundException {
        if(photosRequest == null){
        throw new ResourceNotFoundException("Datos no validos o incompletos");
        }
        var photos = Photos.builder()
                .description(photosRequest.getDescription())
                .url(photosRequest.getUrl())
                .build();
        photosRepository.save(photos);
        return new PhotosResponse("Foto añadida exitosamente");
    }
    public PhotosResponse updatePhoto(Long id,PhotosRequest photosRequest) throws ResourceNotFoundException {
        Photos photos = photosRepository.findById(id).get();
        if(photos == null){
            throw new ResourceNotFoundException("Datos no validos o incompletos");
        }
        photos.setDescription(photosRequest.getDescription());
        photos.setUrl(photosRequest.getUrl());
        photosRepository.save(photos);
        return new PhotosResponse("Datos actualizados exitosamente");
    }
}
