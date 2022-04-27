package com.microservicios.motoservice.services;

import com.microservicios.motoservice.entities.Moto;
import com.microservicios.motoservice.repository.MotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotoService {

    private final MotoRepository motoRepository;

    public List<Moto> getAllByUserId(Integer userId){
        return motoRepository.findAllByUserId(userId);
    }

    public Optional<Moto> getById(Integer id){
        return motoRepository.findById(id);
    }

    public Moto save (Moto moto){
        return motoRepository.save(moto);
    }

}
