package com.name.vehicleregistration.controller;

import com.name.vehicleregistration.controller.dtos.VehicleRequest;
import com.name.vehicleregistration.controller.dtos.VehicleResponse;
import com.name.vehicleregistration.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

  private final VehicleService vehicleService;

  public VehicleController(VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  @Operation(summary = "Add a new vehicle", description = "Adds a new vehicle to the system and returns the created vehicle")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Vehicle successfully added"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @PostMapping
  public ResponseEntity<VehicleResponse> addVehicle(@RequestBody VehicleRequest vehicleRequest) {
    try {
      VehicleResponse savedVehicle = vehicleService.saveVehicle(vehicleRequest);
      return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Get a vehicle by ID", description = "Fetches a vehicle by its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Vehicle found"),
      @ApiResponse(responseCode = "404", description = "Vehicle not found")
  })
  @GetMapping("/{id}")
  public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable Integer id) {
    try {
      VehicleResponse vehicle = vehicleService.getVehicleById(id);
      return new ResponseEntity<>(vehicle, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Update an existing vehicle by ID", description = "Updates the details of an existing vehicle by its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Vehicle updated successfully"),
      @ApiResponse(responseCode = "404", description = "Vehicle not found")
  })
  @PutMapping("/{id}")
  public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable Integer id,
      @RequestBody VehicleRequest vehicleRequest) {
    try {
      VehicleResponse updatedVehicle = vehicleService.updateVehicleById(id, vehicleRequest);
      return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Delete a vehicle by ID", description = "Deletes a vehicle by its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Vehicle successfully deleted"),
      @ApiResponse(responseCode = "404", description = "Vehicle not found")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
    try {
      vehicleService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
