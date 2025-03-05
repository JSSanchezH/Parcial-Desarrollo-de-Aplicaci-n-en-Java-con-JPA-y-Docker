package com.jssanchezh.parcial.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDate date;

  // Many-to-One relationship with Vehicle
  @ManyToOne
  @JoinColumn(name = "vehicle_id", nullable = false)
  private Vehicle vehicle;

  // Many-to-One relationship with Service
  @ManyToOne
  @JoinColumn(name = "service_id", nullable = false)
  private ServiceDetail service;

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public ServiceDetail getService() {
    return service;
  }

  public void setService(ServiceDetail service) {
    this.service = service;
  }
}