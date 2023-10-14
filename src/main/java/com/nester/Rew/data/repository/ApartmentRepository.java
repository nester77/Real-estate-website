package com.nester.Rew.data.repository;

import com.nester.Rew.data.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    @Query(value = "SELECT * FROM apartments a WHERE a.owner_id = :id", nativeQuery = true)
    List<Apartment> findAllByUser(@Param("id") Long id);
}
