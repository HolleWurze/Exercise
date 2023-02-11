package xyz.ITMO.Exercise.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import xyz.ITMO.Exercise.Exceptions.MyException;
import xyz.ITMO.Exercise.Utils.PaginationUtil;
import xyz.ITMO.Exercise.model.dto.CarsDTORequest;
import xyz.ITMO.Exercise.model.dto.CarsDTOResponse;
import xyz.ITMO.Exercise.model.dto.DriverDTORequest;
import xyz.ITMO.Exercise.model.entity.Car;
import xyz.ITMO.Exercise.model.entity.Driver;
import xyz.ITMO.Exercise.model.enums.EnumCarStatus;
import xyz.ITMO.Exercise.model.repository.CarsRepository;
import xyz.ITMO.Exercise.model.repository.DriverRepository;
import xyz.ITMO.Exercise.service.CarsService;
import xyz.ITMO.Exercise.service.DriverService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarsService {

    private final DriverService driverService;
    private final CarsRepository carsRepository;
    private final DriverRepository driverRepository;

    private final ObjectMapper mapper;

    @Override
    public CarsDTORequest create(CarsDTORequest carsDTORequest) {
        carsRepository.findByName(carsDTORequest.getName()).ifPresent(
                c -> {
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
        car.setStatus(EnumCarStatus.UPDATED);

        return mapper.convertValue(carsRepository.save(car), CarsDTORequest.class);
    }

    @Override
    public CarsDTORequest get(String name) {
        return mapper.convertValue(getCar(name), CarsDTORequest.class);
    }

    @Override
    public void delete(String name) {
        Car car = getCar(name);
        car.setStatus(EnumCarStatus.DELETED);
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
        driver.getCars().add(car);
        Driver saveDriver = driverRepository.save(driver);
        return response;
    }

    @Override
    public ModelMap getAllCars(Integer page, Integer perPage, String sort, Sort.Direction order) {
        Pageable pageRequest = PaginationUtil.getPageRequest(page, perPage, sort, order);
        Page<Car> pageResult = carsRepository.findAll(pageRequest);

        List<CarsDTORequest> content = pageResult.getContent().stream()
                .map(h -> mapper.convertValue(h, CarsDTORequest.class))
                .collect(Collectors.toList());

        PagedListHolder<CarsDTORequest> result = new PagedListHolder<>(content);

        result.setPage(page);
        result.setPageSize(perPage);

        ModelMap map = new ModelMap();
        map.addAttribute("content", result.getPageList());
        map.addAttribute("pageNumber", page);
        map.addAttribute("pageSize", result.getPageSize());
        map.addAttribute("totalPages", result.getPageCount());

        return map;
    }

    private Car getCar(String carDTOResponse) {
        return carsRepository.findByName(carDTOResponse)
                .orElseThrow(() -> new MyException("Машина с таким названием не найдена", HttpStatus.NOT_FOUND));
    }
}
