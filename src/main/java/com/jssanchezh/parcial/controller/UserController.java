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

import com.jssanchezh.parcial.model.User;
import com.jssanchezh.parcial.service.UserService;

// Marks this class as a REST controller that handles HTTP requests for /users endpoint
@RestController
@RequestMapping("/users")
public class UserController {

  // Injecting the UserService to manage business logic for User entity
  @Autowired
  private UserService userService;

  // Handles GET requests to /users to retrieve a list of all users
  @GetMapping
  public ResponseEntity<ArrayList<User>> getUsers() {
    ArrayList<User> users = userService.getUsers(); // Calls service to fetch all users
    return new ResponseEntity<>(users, HttpStatus.OK); // Returns users with HTTP status 200
  }

  // Handles GET requests to /users/{id} to retrieve a user by their ID
  @GetMapping(path = "/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
    Optional<User> user = userService.getUserById(id); // Calls service to fetch user by ID
    return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Returns user if found
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Returns 404 if not found
  }

  // Handles POST requests to /users to create a new user
  // @RequestBody maps the request body to a User object
  @PostMapping
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    User savedUser = userService.saveUser(user); // Calls service to save the user
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED); // Returns saved user with HTTP status 201
  }

  // Handles PUT requests to /users/{id} to update a user by their ID
  @PutMapping(path = "/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
    Optional<User> updatedUser = userService.updateUser(id, user); // Calls service to update the user
    return updatedUser.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Returns updated user if found
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Returns 404 if not found
  }

  // Handles DELETE requests to /users/{id} to remove a user by their ID
  // @PathVariable extracts the user ID from the URL
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
    boolean ok = this.userService.deleteUserById(id); // Calls service to delete the user
    if (ok) {
      return new ResponseEntity<>("User deleted successfully with ID: " + id, HttpStatus.OK); // Success message
    } else {
      return new ResponseEntity<>("User not found with ID: " + id, HttpStatus.NOT_FOUND); // Error message if user
                                                                                          // doesn't exist
    }
  }
}
