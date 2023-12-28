package com.sparta.givemetuna.domain.user.repository;

import com.sparta.givemetuna.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}