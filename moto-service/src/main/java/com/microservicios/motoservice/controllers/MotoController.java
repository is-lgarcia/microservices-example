package com.microservicios.motoservice.controllers;

import com.microservicios.motoservice.entities.Moto;
import com.microservicios.motoservice.services.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/moto")
public class MotoController {

    private final MotoService motoService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Moto>> getAllByUserId(@PathVariable Integer userId){
        List<Moto> motos = motoService.getAllByUserId(userId);
        if (motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getById(@PathVariable Integer id){
        return ResponseEntity.of(motoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Moto> save(@RequestBody Moto moto){
        return ResponseEntity.ok(motoService.save(moto));
    }
}