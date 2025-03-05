package com.jssanchezh.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jssanchezh.parcial.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}
