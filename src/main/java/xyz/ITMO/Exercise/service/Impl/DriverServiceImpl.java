package xyz.ITMO.Exercise.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import xyz.ITMO.Exercise.model.dto.CarsDTORequest;
import xyz.ITMO.Exercise.model.dto.DriverDTO;
import xyz.ITMO.Exercise.model.entity.Car;
import xyz.ITMO.Exercise.model.entity.Driver;
import xyz.ITMO.Exercise.model.repository.DriverRepository;
import xyz.ITMO.Exercise.service.DriverService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final ObjectMapper mapper;
    @Override
    public DriverDTO createDriver(DriverDTO driverDTO) {

       // Driver driver = new Driver();
//        driver.setAge(driverDTO.getAge());
//        driver.setFirstName(driverDTO.getFirstName());
//        driver.setLastName(driverDTO.getLastName());
//        driver.setGender(driverDTO.getGender());

        Driver driver = mapper.convertValue(driverDTO, Driver.class);

        driver.setCreatedAt(LocalDateTime.now());

        List<Car> cars = driverDTO.getCars()
                .stream()
                .map(c -> {
                    Car car = new Car();
                    car.setCarModel(c.getCarModel());
                    car.setMaximumLoad(c.getMaximumLoad());
                    try {
                        car.setYearOfManufacture(LocalDate.parse(c.getYearOfManufacture()));
                    } catch (Exception ex) {
                        log.error(ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                    return car;
                })
                .collect(Collectors.toList());

        driver.setCars(cars);

        Driver entity = driverRepository.save(driver);

        DriverDTO result = mapper.convertValue(entity, DriverDTO.class);
        List<CarsDTORequest> carsDTORequest = entity.getCars()
                .stream()
                .map(c -> {
                    CarsDTORequest carDTO = new CarsDTORequest();
                    carDTO.setCarModel(c.getCarModel());
                    carDTO.setMaximumLoad(c.getMaximumLoad());
                    carDTO.setYearOfManufacture(String.valueOf(c.getYearOfManufacture()));
                    return carDTO;
                })
                .collect(Collectors.toList());

        result.setCars(carsDTORequest);


        return result;
    }

    @Override
    public DriverDTO update(DriverDTO userDTO) {
        return null;
    }

    @Override
    public DriverDTO get(String email) {
        return null;
    }

    @Override
    public void delete(String email) {

    }

    @Override
    public Driver getUser(String email) {
        return null;
    }
}
