package com.microservicio.carservice.controller;

import com.microservicio.carservice.entities.Car;
import com.microservicio.carservice.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Car>> getAllById(@PathVariable Integer userId){
        List<Car> cars = carService.getAllCarByUser(userId);
        if (cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Integer id){
        return ResponseEntity.of(carService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        return ResponseEntity.ok(carService.save(car));
    }

}
