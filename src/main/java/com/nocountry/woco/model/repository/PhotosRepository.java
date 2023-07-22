package com.nocountry.woco.model.repository;

import com.nocountry.woco.model.entity.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<Photos,Long> {
}
