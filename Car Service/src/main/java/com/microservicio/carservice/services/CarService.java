package com.microservicio.carservice.services;

import com.microservicio.carservice.entities.Car;
import com.microservicio.carservice.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getAllCarByUser(Integer userId){
        return carRepository.findAllByUserId(userId);
    }

    public Optional<Car> getById(Integer id){
        return carRepository.findById(id);
    }

    public Car save(Car car){
        return carRepository.save(car);
    }
}
