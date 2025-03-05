package com.jssanchezh.parcial.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jssanchezh.parcial.model.User;
import com.jssanchezh.parcial.repository.IUserRepository;

// Marks this class as a service to handle business logic for User entity
@Service
public class UserService {

  // Injecting the repository to access database operations for User
  @Autowired
  private IUserRepository userRepository;

  // Method to get all users
  // Uses findAll() to fetch all user records from the database
  public ArrayList<User> getUsers() {
    return (ArrayList<User>) userRepository.findAll();
  }

  // Method to get a user by ID
  // Uses findById() to fetch a user if exists, wrapped in Optional
  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  // Method to save a user
  // Uses save() to insert or update a user in the database
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  // Method to update a user by ID
  // Uses findById() to check if user exists, then updates the fields
  public Optional<User> updateUser(Long id, User userDetails) {
    Optional<User> existingUser = userRepository.findById(id);
    if (existingUser.isPresent()) {
      User user = existingUser.get();
      user.setName(userDetails.getName());
      user.setEmail(userDetails.getEmail());
      user.setVehicles(userDetails.getVehicles()); // Updates user's vehicles
      return Optional.of(userRepository.save(user)); // Saves updated user
    }
    return Optional.empty(); // Returns empty if user not found
  }

  // Method to delete a user by ID
  // Uses deleteById() to remove a user if exists
  public Boolean deleteUserById(Long id) {
    try {
      userRepository.deleteById(id);
      return true; // Returns true if deletion is successful
    } catch (Exception e) {
      return false; // Returns false if an error occurs
    }
  }
}
