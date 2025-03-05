package com.jssanchezh.parcial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;
import java.util.List;

// Defining the class as a JPA entity to map it to a database table
@Entity
public class User {

  // Primary key attribute
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Name column with a max length of 100 and cannot be null
  @Column(nullable = false, length = 100)
  private String name;

  // Email column with a max length of 100 and cannot be null
  @Column(nullable = false, length = 100, unique = true)
  private String email;

  // One-to-Many relationship with Vehicle
  @OneToMany(mappedBy = "owner")
  @JsonIgnoreProperties("owner")
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }
}
