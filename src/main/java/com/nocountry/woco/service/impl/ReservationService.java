package com.nocountry.woco.service.impl;

import com.nocountry.woco.model.entity.Reservation;
import com.nocountry.woco.model.repository.ReservationRepository;
import com.nocountry.woco.model.request.ReservationRequest;
import com.nocountry.woco.model.response.ReservationResponse;
import com.nocountry.woco.service.IReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservationService {

    private ModelMapper modelMapper;
    private final ReservationRepository reservationRepository;

    public ReservationService(ModelMapper modelMapper, ReservationRepository reservationRepository) {
        this.modelMapper = modelMapper;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = modelMapper.map(reservationRequest, Reservation.class);
        reservationRepository.save(reservation);
        return modelMapper.map(reservation, ReservationResponse.class);
    }

    @Override
    public boolean deleteReservation(Long reservationId) {
        Reservation existingReservation = reservationRepository.findById(reservationId).orElse(null);
        if (existingReservation!= null) {
            reservationRepository.delete(existingReservation);
            return true;
        }
        return false;
    }

    @Override
    public List<ReservationResponse> getAllReservationsByUserId(Long userId) {
        List<Reservation> reservations = reservationRepository.findReservationsByUserEntity_Id(userId);
        if (reservations != null) {
            return reservations.stream()
                    .map(reservation -> modelMapper.map(reservation, ReservationResponse.class))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public ReservationResponse updateReservation(Long id, ReservationRequest reservationRequest){
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);
        if (existingReservation != null) {
            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(reservationRequest, existingReservation);
            Reservation updatedReservation = reservationRepository.save(existingReservation);
            return modelMapper.map(updatedReservation, ReservationResponse.class);
        } else {
            return null;
        }
    }

    @Override
    public ReservationResponse getReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation!= null) {
            return modelMapper.map(reservation, ReservationResponse.class);
        }
        return null;
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
               .map(reservation -> modelMapper.map(reservation, ReservationResponse.class))
               .collect(Collectors.toList());
    }
}
