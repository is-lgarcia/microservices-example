package com.microservicio.carservice.repositories;

import com.microservicio.carservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByUserId(Integer userId);
}
