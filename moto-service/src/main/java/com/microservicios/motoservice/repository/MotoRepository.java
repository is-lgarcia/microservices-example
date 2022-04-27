package com.microservicios.motoservice.repository;

import com.microservicios.motoservice.entities.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepository extends JpaRepository<Moto, Integer> {
    List<Moto> findAllByUserId(Integer userId);
}
