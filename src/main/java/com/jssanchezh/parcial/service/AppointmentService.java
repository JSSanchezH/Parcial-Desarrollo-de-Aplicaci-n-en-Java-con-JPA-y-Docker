package com.jssanchezh.parcial.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jssanchezh.parcial.model.Appointment;
import com.jssanchezh.parcial.repository.IAppointmentRepository;

@Service
public class AppointmentService {

    // Inyección del repositorio de Appointment
    @Autowired
    private IAppointmentRepository appointmentRepository;

    // Método para obtener todas las citas
    public ArrayList<Appointment> getAllAppointments() {
        return (ArrayList<Appointment>) appointmentRepository.findAll();
    }

    // Método para obtener una cita por ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Método para guardar una nueva cita
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Método para actualizar una cita por ID
    public Optional<Appointment> updateAppointment(Long id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setDate(updatedAppointment.getDate());
            appointment.setVehicle(updatedAppointment.getVehicle());
            appointment.setService(updatedAppointment.getService());
            return appointmentRepository.save(appointment);
        });
    }

    // Método para eliminar una cita por ID
    public boolean deleteAppointmentById(Long id) {
        try {
            appointmentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
