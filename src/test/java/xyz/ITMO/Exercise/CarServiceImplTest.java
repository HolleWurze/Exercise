package xyz.ITMO.Exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import xyz.ITMO.Exercise.Exceptions.MyException;
import xyz.ITMO.Exercise.model.dto.CarsDTORequest;
import xyz.ITMO.Exercise.model.dto.CarsDTOResponse;
import xyz.ITMO.Exercise.model.entity.Car;
import xyz.ITMO.Exercise.model.entity.Driver;
import xyz.ITMO.Exercise.model.repository.CarsRepository;
import xyz.ITMO.Exercise.service.DriverService;
import xyz.ITMO.Exercise.service.Impl.CarServiceImpl;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {
    @InjectMocks
    private CarServiceImpl carService;

//    @Mock
//    private UserService userService;

    @Mock
    private CarsRepository carsRepository;

    @Spy
    private ObjectMapper mapper;

    @Test
    public void create() {
        CarsDTORequest request = new CarsDTORequest();
        request.setName("Mechanics");

        when(carsRepository.save(any(Car.class))).thenReturn(any(Car.class));

        CarsDTORequest result = carService.create(request);
        assertEquals(request.getName() , result.getName());
    }

    @Test(expected = MyException.class)
    public void create_exception() {
        CarsDTORequest request = new CarsDTORequest();
        request.setName("Mechanics");

        when(carsRepository.findByName(anyString())).thenThrow(MyException.class);
        carService.create(request);

    }

    @Test
    public void update() {
    }

    @Test
    public void get() {
    }

    @Test
    public void delete() {
        Car car = new Car();
        car.setName("Mechanics");
        when(carsRepository.findByName(anyString())).thenReturn(Optional.of(car));

        when(carsRepository.save(any(Car.class))).thenReturn(any(Car.class));

//        verify(carsRepository, times(1)).save(car);

        carService.delete("Mechanics");

    }

    @Test
    public void addToUser() {
        DriverService driverService = mock(DriverService.class);

        Driver driver = new Driver();
        driver.setEmail("test@test.com");
        driver.setAge(25);

        when(driverService.getDriver(anyString())).thenReturn(driver);
        CarsDTOResponse building = carService.addToDriver("MegaCar", "test@test.com");
        building.getDriverDTORequest().getEmail();
    }

    @Test
    public void getAllCars() {
    }

}
