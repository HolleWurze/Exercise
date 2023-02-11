package xyz.ITMO.Exercise.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import xyz.ITMO.Exercise.Exceptions.MyException;
import xyz.ITMO.Exercise.model.dto.DriverDTORequest;
import xyz.ITMO.Exercise.model.entity.Driver;
import xyz.ITMO.Exercise.model.enums.EnumDriverStatus;
import xyz.ITMO.Exercise.model.repository.DriverRepository;
import xyz.ITMO.Exercise.service.CarsService;
import xyz.ITMO.Exercise.service.DriverService;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final CarsService carsService;
    private final DriverRepository driverRepository;
    private final ObjectMapper mapper;
    @Override
    public DriverDTORequest createDriver(DriverDTORequest driverDTORequest) {
        driverRepository.findByEmail(driverDTORequest.getEmail()).ifPresent(
                h -> {
                    throw new MyException("Водитель с таким email уже существует", HttpStatus.BAD_REQUEST);
                }
        );

        Driver car = mapper.convertValue(driverDTORequest, Driver.class);
        Driver save = driverRepository.save(car);

        return mapper.convertValue(save, DriverDTORequest.class);
    }

    @Override
    public DriverDTORequest updateDriver(DriverDTORequest driverDTORequest) {
        Driver driver = getDriver(driverDTORequest.getFirstName());

        driver.setAge(driverDTORequest.getAge() == null ? driver.getAge() : driverDTORequest.getAge());
        driver.setGender(driverDTORequest.getGender() == null ? driver.getGender() : driverDTORequest.getGender());
        driver.setFirstName(driverDTORequest.getFirstName() == null ? driver.getFirstName() : driverDTORequest.getFirstName());
        driver.setLastName(driverDTORequest.getLastName() == null ? driver.getLastName() : driverDTORequest.getLastName());
        driver.setEmail(driverDTORequest.getEmail() == null ? driver.getEmail() : driverDTORequest.getEmail());
        driver.setUpdatedAt(LocalDateTime.now());
        driver.setStatus(EnumDriverStatus.UPDATED);

        return mapper.convertValue(driverRepository.save(driver), DriverDTORequest.class);
    }

    @Override
    public DriverDTORequest get(String email) {
        return mapper.convertValue(getDriver(email), DriverDTORequest.class);
    }

    @Override
    public void deleteDriver(String email) {
        Driver driver = getDriver(email);
        driver.setStatus(EnumDriverStatus.DELETED);
        driver.setUpdatedAt(LocalDateTime.now());
        driverRepository.save(driver);
    }

    @Override
    public Driver getDriver(String email) {
        return driverRepository.findByEmail(email)
                .orElseThrow(() -> new MyException("Водитель с таким email не найден", HttpStatus.NOT_FOUND));
    }
}
