package com.nocountry.woco.model.response;

import com.nocountry.woco.model.entity.Cowork;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationResponse{

    private Long id;
    private LocalDate dateBefore;
    private LocalDate dateAfter;
    private Double price;
    private Cowork cowork;
    private Long UserId;
}
