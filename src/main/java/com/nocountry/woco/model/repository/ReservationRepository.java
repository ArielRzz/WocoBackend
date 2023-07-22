package com.nocountry.woco.model.repository;

import com.nocountry.woco.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findReservationsByUserEntity_Id(Long userId);
}
