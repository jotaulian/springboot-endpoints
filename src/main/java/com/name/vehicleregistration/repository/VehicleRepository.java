package com.name.vehicleregistration.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.name.vehicleregistration.model.Vehicle;

public class VehicleRepository {

    private final List<Vehicle> vehicleList = new ArrayList<>();

    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicleList);
    }

    public Optional<Vehicle> findById(Integer id) {
        return vehicleList.stream().filter(vehicle -> vehicle.getId().equals(id)).findFirst();
    }

    public Vehicle save(Vehicle vehicle) {
        vehicleList.add(vehicle);
        return vehicle;
    }

    public void deleteById(Integer id) {
        vehicleList.removeIf(vehicle -> vehicle.getId().equals(id));
    }

    public Vehicle update(Vehicle vehicle) {
        deleteById(vehicle.getId());
        vehicleList.add(vehicle);
        return vehicle;
    }
}
