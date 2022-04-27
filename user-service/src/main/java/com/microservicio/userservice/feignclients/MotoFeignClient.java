package com.microservicio.userservice.feignclients;

import com.microservicio.userservice.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "motos", url = "http://localhost:8083")
public interface MotoFeignClient{

    @RequestMapping(method = RequestMethod.POST, value = "/motos", consumes = "application/json")
    Moto save(Moto moto);

    @GetMapping("/motos/users/{userId}")
    List<Moto> getMotos(@PathVariable Integer userId);

}

