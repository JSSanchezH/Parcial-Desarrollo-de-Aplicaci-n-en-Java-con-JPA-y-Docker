package com.jssanchezh.parcial.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String brand;

  @Column(nullable = false, length = 50)
  private String model;

  @Column(nullable = false)
  private int year;

  @Column(nullable = false)
  private double price;

  // Many-to-One relationship with User
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @JsonIgnoreProperties("vehicles")
  private User owner;

  // One-to-One relationship with Insurance
  @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
  private Insurance insurance;

  // One-to-Many relationship with Appointment
  @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("vehicle")
  private List<Appointment> appointments;

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Insurance getInsurance() {
    return insurance;
  }

  public void setInsurance(Insurance insurance) {
    this.insurance = insurance;
  }

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }
}
