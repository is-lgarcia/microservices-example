package com.microservicio.userservice.controllers;

import com.microservicio.userservice.entities.User;
import com.microservicio.userservice.models.Car;
import com.microservicio.userservice.models.Moto;
import com.microservicio.userservice.models.UserDto;
import com.microservicio.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<User>> listUser(){
        List<User> users = service.getAll();
        if (users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        Optional<User> user = service.getUserById(id);
        return ResponseEntity.of(user);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User newUser = service.save(user);
        return ResponseEntity.ok(newUser);
    }

    @CircuitBreaker(name = "carCB", fallbackMethod = "fallbackGetCars")
    @GetMapping("/{userId}/cars")
    public ResponseEntity<List<Car>> getCarByUserId(@PathVariable Integer userId){
        User user = service.getUserById(userId).orElse(null);

        if (user == null){
            return ResponseEntity.notFound().build();
        }

        List<Car> cars = service.getCarByUserId(userId);
        return ResponseEntity.ok(cars);
    }

    @CircuitBreaker(name = "carCB", fallbackMethod = "fallbackSaveCar")
    @PostMapping("/{userId}/cars")
    public ResponseEntity<Car> saveCarByUserId(@PathVariable Integer userId, @RequestBody Car car){
        return ResponseEntity.ok(service.saveCar(userId, car));
    }

    @CircuitBreaker(name = "motoCB", fallbackMethod = "fallbackGetMotos")
    @GetMapping("/{userId}/motos")
    public ResponseEntity<List<Moto>> getMotosByUserId(@PathVariable Integer userId){
        User user = service.getUserById(userId).orElse(null);

        if (user == null){
            return ResponseEntity.notFound().build();
        }

        List<Moto> motos = service.getMotoByUserId(userId);
        return ResponseEntity.ok(motos);
    }

    @CircuitBreaker(name = "motoCB", fallbackMethod = "fallbackSaveMotos")
    @PostMapping("/{userId}/motos")
    public ResponseEntity<Moto> saveMotoByUserId(@PathVariable Integer userId, @RequestBody Moto moto){
        return ResponseEntity.ok(service.saveMoto(userId, moto));
    }

    @CircuitBreaker(name = "todosCB", fallbackMethod = "fallbackGetAllVehicles")
    @GetMapping("/{userId}/vehicles")
    public ResponseEntity<UserDto> getUserAndVehicles(@PathVariable Integer userId){
        return ResponseEntity.ok(service.getUserAndVehicles(userId));
    }

    private ResponseEntity<List<Car>> fallbackGetCars(@PathVariable Integer userId, RuntimeException exception){
        return new ResponseEntity("El usuario: " + userId + " tiene los autos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Car> fallbackSaveCar(@PathVariable Integer userId, @RequestBody Car car, RuntimeException exception){
        return new ResponseEntity("El usuario: " + userId + " no tiene dinero", HttpStatus.OK);
    }

    private ResponseEntity<List<Moto>> fallbackGetMotos(@PathVariable Integer userId, RuntimeException exception){
        return new ResponseEntity("El usuario: " + userId + " tiene las motos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Moto> fallbackSaveMotos(@PathVariable Integer userId, @RequestBody Moto moto, RuntimeException exception){
        return new ResponseEntity("El usuario: " + userId + " no tiene dinero", HttpStatus.OK);
    }

    private ResponseEntity<UserDto> fallbackGetAllVehicles(@PathVariable Integer userId, RuntimeException exception){
        return new ResponseEntity("El usuario: " + userId + " tiene los vehiculos en el taller", HttpStatus.OK);
    }


}
