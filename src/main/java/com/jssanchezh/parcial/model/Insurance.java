package com.jssanchezh.parcial.model;

import jakarta.persistence.*;

@Entity
public class Insurance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String provider;

  @Column(nullable = false)
  private double cost;

  // One-to-One relationship with Vehicle
  @OneToOne
  @JoinColumn(name = "vehicle_id", nullable = false)
  private Vehicle vehicle;

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }
}
