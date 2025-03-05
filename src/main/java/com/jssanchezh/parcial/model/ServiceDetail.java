package com.jssanchezh.parcial.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ServiceDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false)
  private double cost;

  // Many-to-Many relationship with Vehicle
  @ManyToMany
  @JsonIgnoreProperties("services")
  @JoinTable(name = "vehicle_service", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
  private List<Vehicle> vehicles;

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }

}
