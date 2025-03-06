package com.jssanchezh.parcial.controller;

// Importing necessary libraries for REST controllers and dependency injection
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jssanchezh.parcial.model.Vehicle;
import com.jssanchezh.parcial.service.VehicleService;

// Marks this class as a REST controller that handles HTTP requests for /vehicles endpoint
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

  // Injecting the VehicleService to manage business logic for Vehicle entity
  @Autowired
  private VehicleService vehicleService;

  // Handles GET requests to /vehicles to retrieve a list of all vehicles
  @GetMapping
  public ResponseEntity<ArrayList<Vehicle>> getVehicles() {
    ArrayList<Vehicle> vehicles = vehicleService.getVehicles(); // Calls service to fetch all vehicles
    return new ResponseEntity<>(vehicles, HttpStatus.OK); // Returns vehicles with HTTP status 200
  }

  // Handles GET requests to /vehicles/{id} to retrieve a vehicle by its ID
  @GetMapping(path = "/{id}")
  public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long id) {
    Optional<Vehicle> vehicle = vehicleService.getVehicleById(id); // Calls service to fetch vehicle by ID
    return vehicle.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Returns vehicle if found
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Returns 404 if not found
  }

  // Handles POST requests to /vehicles to create a new vehicle
  // @RequestBody maps the request body to a Vehicle object
  @PostMapping
  public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle vehicle) {
    Vehicle savedVehicle = vehicleService.saveVehicle(vehicle); // Calls service to save the vehicle
    return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED); // Returns saved vehicle with HTTP status 201
  }

  // Handles PUT requests to /vehicles/{id} to update a vehicle by its ID
  @PutMapping(path = "/{id}")
  public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") Long id, @RequestBody Vehicle vehicle) {
    Optional<Vehicle> updatedVehicle = vehicleService.updateVehicle(id, vehicle); // Calls service to update the vehicle
    return updatedVehicle.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Returns updated vehicle if found
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Returns 404 if not found
  }

  // Handles DELETE requests to /vehicles/{id} to remove a vehicle by its ID
  // @PathVariable extracts the vehicle ID from the URL
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> deleteVehicleById(@PathVariable("id") Long id) {
    boolean ok = this.vehicleService.deleteVehicleById(id); // Calls service to delete the vehicle
    if (ok) {
      return new ResponseEntity<>("Vehicle deleted successfully with ID: " + id, HttpStatus.OK); // Success message
    } else {
      return new ResponseEntity<>("Vehicle not found with ID: " + id, HttpStatus.NOT_FOUND); // Error message if vehicle
                                                                                             // doesn't exist
    }
  }
}
