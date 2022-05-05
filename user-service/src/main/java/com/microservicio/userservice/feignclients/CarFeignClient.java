package com.microservicio.userservice.feignclients;

import com.microservicio.userservice.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarFeignClient{

    @RequestMapping(method = RequestMethod.POST, value = "/car", consumes = "application/json")
    Car save(Car car);

    @GetMapping("/car/users/{userId}")
    List<Car> getCars(@PathVariable Integer userId);
}
