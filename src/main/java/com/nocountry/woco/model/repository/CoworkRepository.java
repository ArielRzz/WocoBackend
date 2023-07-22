package com.nocountry.woco.model.repository;

import com.nocountry.woco.model.entity.Cowork;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CoworkRepository extends JpaRepository<Cowork,Long >, JpaSpecificationExecutor<Cowork> {
    @Query(value = "SELECT * " +
            " FROM experiences " +
            " WHERE (6371 * ACOS(COS(RADIANS(:latitude_param)) " +
            " * COS(RADIANS(latitude)) * COS(RADIANS(:longitude_param) " +
            " - RADIANS(longitude)) + SIN(RADIANS(:latitude_param))" +
            " * SIN(RADIANS(latitude)))) <= :distance_param", nativeQuery = true)
    List<Cowork> findByLatitudeLongitudeAndDistance(@Param("latitude_param") float latitude, @Param("longitude_param") float longitude, @Param("distance_param") float distance);

    @Query(value = "SELECT * " +
            " FROM coworks " +
            " WHERE  services_id = :services_id_param AND" +
            " (6371 * ACOS(COS(RADIANS(:latitude_param)) " +
            " * COS(RADIANS(latitude)) * COS(RADIANS(:longitude_param) " +
            " - RADIANS(longitude)) + SIN(RADIANS(:latitude_param))" +
            " * SIN(RADIANS(latitude)))) <= :distance_param" +
            " ", nativeQuery = true)
    List<Cowork> findByLatitudeLongitudeDistanceAndService(@Param("latitude_param") float latitude, @Param("longitude_param") float longitude, @Param("distance_param") float distance, @Param("services_id_param") long serviceId);

    @Query(value = "SELECT * " +
            " FROM coworks " +
            " WHERE  services_id = :services_id_param AND" +
            " location_id = :location_id_param", nativeQuery = true)
    List<Cowork> findByServicesIdAndCityId(@Param("services_id_param") long ServicesId, @Param("location_id_param") long locationId);

    List<Cowork> findByServices_id(long id);

    List<Cowork> findByLocation_id(long id);

    List<Cowork> findByNameContaining(String name);

    List<Cowork> findAll(Specification<Cowork> spec);

}
