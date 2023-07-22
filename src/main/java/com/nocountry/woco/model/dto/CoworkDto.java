package com.nocountry.woco.model.dto;

import com.nocountry.woco.model.entity.Rating;
import com.nocountry.woco.model.response.ServiceResponse;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CoworkDto {
    private int id;
    private String name;
    private String description;
    //    private Set<PhotosDto> photosDto = new HashSet<>();
    //private LocationDto category;
    //private ContactDTO contact;
    //private String address;
    //private float latitude;
    //private float longitude;
    private double pricePerDay;
    private int capacity;
    private Set<Rating> ratingsDto = new HashSet<>();
    //private String zipCode;
    private HashSet<ServiceResponse> servicesResponse;
}