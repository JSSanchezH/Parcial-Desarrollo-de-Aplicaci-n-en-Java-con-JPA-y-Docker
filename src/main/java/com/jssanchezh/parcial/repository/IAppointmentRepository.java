package com.jssanchezh.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jssanchezh.parcial.model.Appointment;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

}
