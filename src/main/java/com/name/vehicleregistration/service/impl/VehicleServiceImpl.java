package com.name.vehicleregistration.service.impl;

import java.util.Optional;

import com.name.vehicleregistration.controller.dtos.VehicleRequest;
import com.name.vehicleregistration.controller.dtos.VehicleResponse;
import com.name.vehicleregistration.model.Vehicle;
import com.name.vehicleregistration.repository.VehicleRepository;
import com.name.vehicleregistration.service.VehicleService;

public class VehicleServiceImpl implements VehicleService {

  private final VehicleRepository vehicleRepository = new VehicleRepository();

  @Override
  public VehicleResponse getVehicleById(Integer id) throws Exception {
    Optional<Vehicle> vehicle = vehicleRepository.findById(id);
    if (vehicle.isPresent()) {
      return this.mapToResponse(vehicle.get());
    } else {
      throw new Exception("Vehicle not found");
    }
  }

  @Override
  public void deleteById(Integer id) throws Exception {
    vehicleRepository.deleteById(id);
  }

  @Override
  public VehicleResponse updateVehicleById(Integer id, VehicleRequest vehicleRequest) throws Exception {
    // Buscar el vehículo por ID
    Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);

    // Si el vehículo está presente, realizar la actualización
    if (vehicleOptional.isPresent()) {
      Vehicle vehicle = mapToEntity(vehicleRequest); // Mapear la solicitud al objeto entidad Vehicle
      vehicle.setId(id); // Asegurar que el ID no cambie
      vehicleRepository.update(vehicle); // Actualizar el vehículo en el repositorio
      return this.mapToResponse(vehicle); // Retornar la respuesta mapeada
    } else {
      throw new Exception("Vehicle not found");
    }
  }

  @Override
  public VehicleResponse saveVehicle(VehicleRequest vehicleRequest) throws Exception {
    Vehicle vehicle = this.mapToEntity(vehicleRequest);
    vehicleRepository.save(vehicle);
    return this.mapToResponse(vehicle);
  }

  // Método auxiliar para mapear VehicleRequest a Vehicle (entidad)
  private Vehicle mapToEntity(VehicleRequest vehicleRequest) {
    Vehicle vehicle = new Vehicle();
    vehicle.setBrand(vehicleRequest.getBrand());
    vehicle.setModel(vehicleRequest.getModel());
    vehicle.setMileage(vehicleRequest.getMileage());
    vehicle.setPrice(vehicleRequest.getPrice());
    vehicle.setYear(vehicleRequest.getYear());
    vehicle.setDescription(vehicleRequest.getDescription());
    vehicle.setColour(vehicleRequest.getColour());
    vehicle.setFuelType(vehicleRequest.getFuelType());
    vehicle.setNumDoors(vehicleRequest.getNumDoors());
    return vehicle;
  }

  // Método auxiliar para mapear Vehicle (entidad) a VehicleResponse (DTO)
  private VehicleResponse mapToResponse(Vehicle vehicle) {
    VehicleResponse vehicleResponse = new VehicleResponse();
    vehicleResponse.setId(vehicle.getId());
    vehicleResponse.setBrand(vehicle.getBrand());
    vehicleResponse.setModel(vehicle.getModel());
    vehicleResponse.setMileage(vehicle.getMileage());
    vehicleResponse.setPrice(vehicle.getPrice());
    vehicleResponse.setYear(vehicle.getYear());
    vehicleResponse.setDescription(vehicle.getDescription());
    vehicleResponse.setColour(vehicle.getColour());
    vehicleResponse.setFuelType(vehicle.getFuelType());
    vehicleResponse.setNumDoors(vehicle.getNumDoors());
    return vehicleResponse;
  }
}
