package xyz.ITMO.Exercise.service;

import xyz.ITMO.Exercise.model.dto.DriverDTO;
import xyz.ITMO.Exercise.model.entity.Driver;

public interface DriverService {


    DriverDTO createDriver(DriverDTO driverDTO);

    DriverDTO update(DriverDTO driverDTO);

    DriverDTO get(String email);

    void delete(String email);

    Driver getUser(String email);
}
