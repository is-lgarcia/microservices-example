package com.microservicio.userservice.repositories;

import com.microservicio.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposository extends JpaRepository<User, Integer> {
}
