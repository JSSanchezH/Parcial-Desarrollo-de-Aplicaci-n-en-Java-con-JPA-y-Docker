package com.jssanchezh.parcial.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jssanchezh.parcial.model.ServiceDetail;
import com.jssanchezh.parcial.service.ServiceDetailService;

@RestController
@RequestMapping("/services")
public class ServiceDetailController {

  // Inyección del ServiceDetailService
  @Autowired
  private ServiceDetailService serviceDetailService;

  // Obtener todos los ServiceDetail
  @GetMapping
  public ResponseEntity<ArrayList<ServiceDetail>> getAllServiceDetails() {
    ArrayList<ServiceDetail> services = serviceDetailService.getAllServiceDetails();
    return new ResponseEntity<>(services, HttpStatus.OK);
  }

  // Obtener un ServiceDetail por ID
  @GetMapping("/{id}")
  public ResponseEntity<ServiceDetail> getServiceDetailById(@PathVariable Long id) {
    Optional<ServiceDetail> service = serviceDetailService.getServiceDetailById(id);
    return service.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Crear un nuevo ServiceDetail
  @PostMapping
  public ResponseEntity<ServiceDetail> saveServiceDetail(@RequestBody ServiceDetail serviceDetail) {
    ServiceDetail savedService = serviceDetailService.saveServiceDetail(serviceDetail);
    return new ResponseEntity<>(savedService, HttpStatus.CREATED);
  }

  // Actualizar un ServiceDetail existente
  @PutMapping("/{id}")
  public ResponseEntity<ServiceDetail> updateServiceDetail(@PathVariable Long id,
      @RequestBody ServiceDetail serviceDetail) {
    Optional<ServiceDetail> existingService = serviceDetailService.getServiceDetailById(id);
    if (existingService.isPresent()) {
      ServiceDetail updatedService = existingService.get();
      updatedService.setName(serviceDetail.getName());
      updatedService.setCost(serviceDetail.getCost());
      updatedService.setVehicles(serviceDetail.getVehicles());
      serviceDetailService.saveServiceDetail(updatedService);
      return new ResponseEntity<>(updatedService, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Eliminar un ServiceDetail por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteServiceDetailById(@PathVariable Long id) {
    boolean isDeleted = serviceDetailService.deleteServiceDetailById(id);
    if (isDeleted) {
      return new ResponseEntity<>("ServiceDetail eliminado correctamente.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("No se encontró el ServiceDetail.", HttpStatus.NOT_FOUND);
    }
  }
}
