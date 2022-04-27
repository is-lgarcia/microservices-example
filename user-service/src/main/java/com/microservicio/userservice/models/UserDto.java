package com.microservicio.userservice.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private List<Car> cars;
    private List<Moto> motos;
}
