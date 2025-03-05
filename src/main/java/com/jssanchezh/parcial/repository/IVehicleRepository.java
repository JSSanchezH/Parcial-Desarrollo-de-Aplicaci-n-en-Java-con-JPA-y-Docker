package com.jssanchezh.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jssanchezh.parcial.model.Vehicle;

public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

}
