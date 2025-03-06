package com.jssanchezh.parcial.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jssanchezh.parcial.model.Appointment;
import com.jssanchezh.parcial.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

  // Inyección del servicio de Appointment
  @Autowired
  private AppointmentService appointmentService;

  // Obtener todas las citas
  @GetMapping
  public ResponseEntity<ArrayList<Appointment>> getAllAppointments() {
    ArrayList<Appointment> appointments = appointmentService.getAllAppointments();
    return new ResponseEntity<>(appointments, HttpStatus.OK);
  }

  // Obtener una cita por ID
  @GetMapping("/{id}")
  public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Long id) {
    Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
    return appointment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Guardar una nueva cita
  @PostMapping
  public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
    Appointment savedAppointment = appointmentService.saveAppointment(appointment);
    return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
  }

  // Actualizar una cita existente por ID
  @PutMapping("/{id}")
  public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") Long id,
      @RequestBody Appointment updatedAppointment) {
    Optional<Appointment> appointment = appointmentService.updateAppointment(id, updatedAppointment);
    return appointment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Eliminar una cita por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteAppointmentById(@PathVariable("id") Long id) {
    boolean ok = appointmentService.deleteAppointmentById(id);
    if (ok) {
      return new ResponseEntity<>("Appointment deleted successfully with ID: " + id, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Appointment not found with ID: " + id, HttpStatus.NOT_FOUND);
    }
  }
}
