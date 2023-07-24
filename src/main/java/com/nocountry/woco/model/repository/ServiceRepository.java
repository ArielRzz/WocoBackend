package com.nocountry.woco.model.repository;

import com.nocountry.woco.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRepository extends JpaRepository<Services,Long> {

}
