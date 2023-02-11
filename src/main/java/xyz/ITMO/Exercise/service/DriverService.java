package xyz.ITMO.Exercise.service;

import org.springframework.stereotype.Service;
import xyz.ITMO.Exercise.model.dto.DriverDTORequest;
import xyz.ITMO.Exercise.model.entity.Driver;

@Service
public interface DriverService {


    DriverDTORequest createDriver(DriverDTORequest driverDTORequest);

    DriverDTORequest updateDriver(DriverDTORequest driverDTORequest);

    DriverDTORequest get(String email);

    void deleteDriver(String email);

    Driver getDriver(String email);

}
