package com.microservicio.userservice.feignclients;

import com.microservicio.userservice.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "moto-service")
public interface MotoFeignClient{

    @RequestMapping(method = RequestMethod.POST, value = "/moto", consumes = "application/json")
    Moto save(Moto moto);

    @GetMapping("/moto/users/{userId}")
    List<Moto> getMotos(@PathVariable Integer userId);

}

