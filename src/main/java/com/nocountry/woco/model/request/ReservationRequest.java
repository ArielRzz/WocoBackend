package com.nocountry.woco.model.request;

import com.nocountry.woco.model.entity.Cowork;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private Long id;
    private LocalDate dateBefore;
    private LocalDate dateAfter;
    private Double price;
    private Cowork cowork;
    //private UserRequest userRequest;
}
