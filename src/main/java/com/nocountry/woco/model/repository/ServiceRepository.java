package com.nocountry.woco.model.repository;

import com.nocountry.woco.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Services,Long> {

    List<Services> findAllByPrice(Double price);
}
