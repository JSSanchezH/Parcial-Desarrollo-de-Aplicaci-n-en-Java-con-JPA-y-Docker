package com.jssanchezh.parcial.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jssanchezh.parcial.model.Vehicle;
import com.jssanchezh.parcial.repository.IVehicleRepository;

// Marks this class as a service to handle business logic for Vehicle entity
@Service
public class VehicleService {

  // Injecting the repository to access database operations for Vehicle
  @Autowired
  private IVehicleRepository vehicleRepository;

  // Method to get all vehicles
  // Uses findAll() to fetch all vehicle records from the database
  public ArrayList<Vehicle> getVehicles() {
    return (ArrayList<Vehicle>) vehicleRepository.findAll();
  }

  // Method to get a vehicle by ID
  // Uses findById() to fetch a vehicle if exists, wrapped in Optional
  public Optional<Vehicle> getVehicleById(Long id) {
    return vehicleRepository.findById(id);
  }

  // Method to save a vehicle
  // Uses save() to insert or update a vehicle in the database
  public Vehicle saveVehicle(Vehicle vehicle) {
    // Ensures cascading save for insurance if present
    if (vehicle.getInsurance() != null) {
      vehicle.getInsurance().setVehicle(vehicle);
    }
    return vehicleRepository.save(vehicle);
  }

  // Method to update a vehicle by ID
  // Uses findById() to check if vehicle exists, then updates the fields
  public Optional<Vehicle> updateVehicle(Long id, Vehicle vehicleDetails) {
    Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
    if (existingVehicle.isPresent()) {
      Vehicle vehicle = existingVehicle.get();
      vehicle.setBrand(vehicleDetails.getBrand());
      vehicle.setModel(vehicleDetails.getModel());
      vehicle.setYear(vehicleDetails.getYear());
      vehicle.setPrice(vehicleDetails.getPrice());
      vehicle.setOwner(vehicleDetails.getOwner());

      // Updates insurance information if present
      if (vehicleDetails.getInsurance() != null) {
        vehicleDetails.getInsurance().setVehicle(vehicle);
        vehicle.setInsurance(vehicleDetails.getInsurance());
      }

      return Optional.of(vehicleRepository.save(vehicle)); // Saves updated vehicle
    }
    return Optional.empty(); // Returns empty if vehicle not found
  }

  // Method to delete a vehicle by ID
  // Uses deleteById() to remove a vehicle if exists
  public Boolean deleteVehicleById(Long id) {
    try {
      vehicleRepository.deleteById(id);
      return true; // Returns true if deletion is successful
    } catch (Exception e) {
      return false; // Returns false if an error occurs
    }
  }
}
