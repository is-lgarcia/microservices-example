package com.microservicio.userservice.feignclients;

import com.microservicio.userservice.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "cars", url = "http://localhost:8082/")
public interface CarFeignClient{

    @RequestMapping(method = RequestMethod.POST, value = "/cars", consumes = "application/json")
    Car save(Car car);

    @GetMapping("/cars/users/{userId}")
    List<Car> getCars(@PathVariable Integer userId);
}
