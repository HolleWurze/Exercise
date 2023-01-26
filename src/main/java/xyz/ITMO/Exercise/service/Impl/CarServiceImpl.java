package xyz.ITMO.Exercise.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xyz.ITMO.Exercise.Exceptions.MyException;
import xyz.ITMO.Exercise.model.dto.CarsDTORequest;
import xyz.ITMO.Exercise.model.dto.CarsDTOResponse;
import xyz.ITMO.Exercise.model.dto.DriverDTORequest;
import xyz.ITMO.Exercise.model.entity.Car;
import xyz.ITMO.Exercise.model.entity.Driver;
import xyz.ITMO.Exercise.model.enums.CarStatusEnum;
import xyz.ITMO.Exercise.model.repository.CarsRepository;
import xyz.ITMO.Exercise.service.CarsService;
import xyz.ITMO.Exercise.service.DriverService;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarsService {

    private final DriverService driverService;
    private final CarsRepository carsRepository;
    private final ObjectMapper mapper;

    @Override
    public CarsDTORequest create(CarsDTORequest carsDTORequest) {
        carsRepository.findByName(carsDTORequest.getName()).ifPresent(
                h -> {
                    throw new MyException("Машина с таким названием уже существует", HttpStatus.BAD_REQUEST);
                }
        );

        Car car = mapper.convertValue(carsDTORequest, Car.class);
        Car save = carsRepository.save(car);

        return mapper.convertValue(save, CarsDTORequest.class);
    }

    @Override
    public CarsDTORequest update(CarsDTORequest carsDTORequest) {
        Car car = getCar(carsDTORequest.getName());

        car.setCarModel(carsDTORequest.getCarModel() == null ? car.getCarModel() : carsDTORequest.getCarModel());
        car.setYearOfManufacture(carsDTORequest.getYearOfManufacture() == null ? car.getYearOfManufacture() : carsDTORequest.getYearOfManufacture());
        car.setMaximumLoad(carsDTORequest.getMaximumLoad() == null ? car.getMaximumLoad() : carsDTORequest.getMaximumLoad());
        car.setUpdatedAt(LocalDateTime.now());
        car.setStatus(CarStatusEnum.UPDATED);

        return mapper.convertValue(carsRepository.save(car), CarsDTORequest.class);
    }

    @Override
    public CarsDTORequest get(String name) {
        return mapper.convertValue(getCar(name), CarsDTORequest.class);
    }

    @Override
    public void delete(String name) {
        Car car = getCar(name);
        car.setStatus(CarStatusEnum.DELETED);
        car.setUpdatedAt(LocalDateTime.now());
        carsRepository.save(car);
    }

    @Override
    public CarsDTOResponse addToDriver(String name, String email) {
        Driver driver = driverService.getDriver(email);
        Car car = getCar(name);
        car.setDriver(driver);
        Car save = carsRepository.save(car);
        CarsDTOResponse response = mapper.convertValue(save, CarsDTOResponse.class);
        response.setDriverDTORequest(mapper.convertValue(driver, DriverDTORequest.class));
        return response;
    }

    private Car getCar(String houseDTO) {
        return carsRepository.findByName(houseDTO)
                .orElseThrow(() -> new MyException("Машина с таким названием не найдена", HttpStatus.NOT_FOUND));
    }
}
