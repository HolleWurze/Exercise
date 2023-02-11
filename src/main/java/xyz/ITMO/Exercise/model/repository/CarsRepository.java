package xyz.ITMO.Exercise.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.ITMO.Exercise.model.entity.Car;
import xyz.ITMO.Exercise.model.enums.CarModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByName(String name);

    List<Car> findAllByModel(CarModel carModel);

    @Query("select car from Car car where car.carModel = :carModel")
    List<Car> getCars(@Param("model") CarModel carModel);

    @Query(value = "select * from car where car.yearOfManufacture = :yearOfManufacture", nativeQuery = true)
    List<Car> getCarsNative(@Param("yearOfManufacture") CarModel carModel);

}
