package xyz.ITMO.Exercise.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.ITMO.Exercise.model.entity.Parking;
import xyz.ITMO.Exercise.model.enums.ParkingAddresses;


import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository {
    Optional<Parking> findByName(String name);

    List<Parking> findAllByAddresses(ParkingAddresses parkingAddresses);

    @Query("select parking from Parking parking where parking.addressParking = :addressParking")
    List<Parking> getParking(@Param("address") ParkingAddresses parkingAddresses);

    @Query(value = "select * from parking where parking.workingHours = :workingHours", nativeQuery = true)
    List<Parking> getParkingNative(@Param("workingHours") ParkingAddresses parkingAddresses);
}
