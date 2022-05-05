package com.microservicio.userservice.services;

import com.microservicio.userservice.entities.User;
import com.microservicio.userservice.feignclients.CarFeignClient;
import com.microservicio.userservice.feignclients.MotoFeignClient;
import com.microservicio.userservice.models.Car;
import com.microservicio.userservice.models.Moto;
import com.microservicio.userservice.models.UserDto;
import com.microservicio.userservice.repositories.UserReposository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReposository reposository;
    private final RestTemplate restTemplate;
    private final CarFeignClient carFeignClient;
    private final MotoFeignClient motoFeignClient;

    public List<User> getAll(){
        return reposository.findAll();
    }

    public List<Car> getCarByUserId(Integer userId){
        return carFeignClient.getCars(userId);
    }

    public Car saveCar(Integer userId, Car car){
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public List<Moto> getMotoByUserId(Integer userId){
        return motoFeignClient.getMotos(userId);
    }

    public Moto saveMoto(Integer userId, Moto moto){
        moto.setUserId(userId);
        return motoFeignClient.save(moto);
    }

    public Optional<User> getUserById(Integer id){
        return reposository.findById(id);
    }

    public User save(User user){
        return reposository.save(user);
    }

    public UserDto getUserAndVehicles(Integer userId){
        User user = reposository.findById(userId).orElse(null);

        if (user == null) return null;
        List<Car> cars = carFeignClient.getCars(userId);
        List<Moto> motos = motoFeignClient.getMotos(userId);

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .cars(cars)
                .motos(motos)
                .build();
    }
}
