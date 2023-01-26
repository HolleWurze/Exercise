package xyz.ITMO.Exercise.service;

import xyz.ITMO.Exercise.model.dto.DriverDTORequest;
import xyz.ITMO.Exercise.model.entity.Driver;

public interface DriverService {


    DriverDTORequest createDriver(DriverDTORequest driverDTORequest);

    DriverDTORequest update(DriverDTORequest driverDTORequest);

    DriverDTORequest get(String email);

    void delete(String email);

    Driver getDriver(String email);


}
