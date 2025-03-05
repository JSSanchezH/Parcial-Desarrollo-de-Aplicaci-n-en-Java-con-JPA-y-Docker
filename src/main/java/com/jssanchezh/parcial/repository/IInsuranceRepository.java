package com.jssanchezh.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jssanchezh.parcial.model.Insurance;

public interface IInsuranceRepository extends JpaRepository<Insurance, Long> {

}
