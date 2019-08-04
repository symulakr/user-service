package com.symulakr.service.user.service.repository;

import com.symulakr.service.user.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
