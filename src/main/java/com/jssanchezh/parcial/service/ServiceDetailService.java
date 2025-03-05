package com.jssanchezh.parcial.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jssanchezh.parcial.model.ServiceDetail;
import com.jssanchezh.parcial.repository.IServiceDetailRepository;

@Service
public class ServiceDetailService {

    // Inyección del repositorio
    @Autowired
    private IServiceDetailRepository serviceDetailRepository;

    // Método para obtener todos los ServiceDetail
    public ArrayList<ServiceDetail> getAllServiceDetails() {
        return (ArrayList<ServiceDetail>) serviceDetailRepository.findAll();
    }

    // Método para guardar un nuevo ServiceDetail
    public ServiceDetail saveServiceDetail(ServiceDetail serviceDetail) {
        return serviceDetailRepository.save(serviceDetail);
    }

    // Método para obtener un ServiceDetail por ID
    public Optional<ServiceDetail> getServiceDetailById(Long id) {
        return serviceDetailRepository.findById(id);
    }

    // Método para eliminar un ServiceDetail por ID
    public boolean deleteServiceDetailById(Long id) {
        try {
            serviceDetailRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
