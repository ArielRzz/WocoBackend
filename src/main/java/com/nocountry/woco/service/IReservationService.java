package com.nocountry.woco.service;

import com.nocountry.woco.model.request.ReservationRequest;
import com.nocountry.woco.model.response.ReservationResponse;

import java.util.List;

public interface IReservationService {
    ReservationResponse createReservation(ReservationRequest reservationRequest);
    ReservationResponse getReservationById(Long reservationId);
    ReservationResponse updateReservation(Long id, ReservationRequest reservationRequest);
    boolean deleteReservation(Long reservationId);
    List<ReservationResponse> getAllReservationsByUserId(Long userId);
    List<ReservationResponse> getAllReservations();

}
