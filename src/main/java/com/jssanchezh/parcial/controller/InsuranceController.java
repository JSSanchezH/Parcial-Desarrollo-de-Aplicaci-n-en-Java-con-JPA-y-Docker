package com.jssanchezh.parcial.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jssanchezh.parcial.model.Insurance;
import com.jssanchezh.parcial.service.InsuranceService;

@RestController
@RequestMapping("/insurances")
public class InsuranceController {

  // Inyección del servicio de Insurance
  @Autowired
  private InsuranceService insuranceService;

  // Obtener todos los insurances
  @GetMapping
  public ResponseEntity<ArrayList<Insurance>> getAllInsurances() {
    ArrayList<Insurance> insurances = insuranceService.getAllInsurances();
    return new ResponseEntity<>(insurances, HttpStatus.OK);
  }

  // Obtener un insurance por ID
  @GetMapping("/{id}")
  public ResponseEntity<Insurance> getInsuranceById(@PathVariable("id") Long id) {
    Optional<Insurance> insurance = insuranceService.getInsuranceById(id);
    return insurance.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Guardar un nuevo insurance
  @PostMapping
  public ResponseEntity<Insurance> saveInsurance(@RequestBody Insurance insurance) {
    Insurance savedInsurance = insuranceService.saveInsurance(insurance);
    return new ResponseEntity<>(savedInsurance, HttpStatus.CREATED);
  }

  // Actualizar un insurance por ID
  @PutMapping("/{id}")
  public ResponseEntity<Insurance> updateInsurance(@PathVariable("id") Long id, @RequestBody Insurance insurance) {
    Optional<Insurance> updatedInsurance = insuranceService.updateInsurance(id, insurance);
    return updatedInsurance.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Eliminar un insurance por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteInsuranceById(@PathVariable("id") Long id) {
    boolean ok = insuranceService.deleteInsuranceById(id);
    if (ok) {
      return new ResponseEntity<>("Insurance deleted successfully with ID: " + id, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Insurance not found with ID: " + id, HttpStatus.NOT_FOUND);
    }
  }
}
