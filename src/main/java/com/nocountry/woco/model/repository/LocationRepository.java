package com.nocountry.woco.model.repository;

import com.nocountry.woco.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long > {

    @Query(value = "SELECT * FROM locations as l  WHERE l.city LIKE :search OR  l.province LIKE :search OR  l.country LIKE :search", nativeQuery = true)
    List<Location> findAllByCityOrProvinceOrCountryContaining(@Param("search") String search);

    List<Location> findByCityContaining(String city);

}
