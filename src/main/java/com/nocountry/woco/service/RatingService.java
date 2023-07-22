package com.nocountry.woco.service;

import com.nocountry.woco.model.entity.Rating;
import com.nocountry.woco.model.exception.ResourceNotFoundException;
import com.nocountry.woco.model.repository.RatingRepository;
import com.nocountry.woco.model.request.RatingRequest;
import com.nocountry.woco.model.response.RatingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingResponse addRating(RatingRequest ratingRequest) throws ResourceNotFoundException {
        if(ratingRequest == null){
            throw new ResourceNotFoundException("Error al ingresar la validacion o el comentario");
        }
        var rating = Rating.builder()
                .rating(ratingRequest.getRating())
                .comment(ratingRequest.getComment())
                .build();
        ratingRepository.save(rating);
        return new RatingResponse("Opinion añadida exitosamente");
    }
    public RatingResponse updateRating(Long id,RatingRequest ratingRequest) throws ResourceNotFoundException {
        Rating rating = ratingRepository.findById(id).get();
        if(rating == null){
            throw new ResourceNotFoundException("Error al actualizar la validacion o el comentario");
        }
        rating.setRating(ratingRequest.getRating());
        rating.setComment(ratingRequest.getComment());
        ratingRepository.save(rating);
        return new RatingResponse("Opinion actualizada exitosamente");
    }
}
