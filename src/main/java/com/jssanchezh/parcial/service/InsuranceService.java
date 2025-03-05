package com.jssanchezh.parcial.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jssanchezh.parcial.model.Insurance;
import com.jssanchezh.parcial.repository.IInsuranceRepository;

@Service
public class InsuranceService {

  // Inyección del repositorio de Insurance
  @Autowired
  private IInsuranceRepository insuranceRepository;

  // Obtener todos los Insurance
  public ArrayList<Insurance> getAllInsurances() {
    return (ArrayList<Insurance>) insuranceRepository.findAll();
  }

  // Guardar un nuevo Insurance
  public Insurance saveInsurance(Insurance insurance) {
    return insuranceRepository.save(insurance);
  }

  // Obtener un Insurance por ID
  public Optional<Insurance> getInsuranceById(Long id) {
    return insuranceRepository.findById(id);
  }

  // Eliminar un Insurance por ID
  public boolean deleteInsuranceById(Long id) {
    try {
      insuranceRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // Actualizar un Insurance existente
  public Optional<Insurance> updateInsurance(Long id, Insurance insurance) {
    Optional<Insurance> existingInsurance = insuranceRepository.findById(id);
    if (existingInsurance.isPresent()) {
      Insurance updatedInsurance = existingInsurance.get();
      updatedInsurance.setProvider(insurance.getProvider());
      updatedInsurance.setCost(insurance.getCost());
      updatedInsurance.setVehicle(insurance.getVehicle());
      insuranceRepository.save(updatedInsurance);
      return Optional.of(updatedInsurance);
    } else {
      return Optional.empty();
    }
  }
}
