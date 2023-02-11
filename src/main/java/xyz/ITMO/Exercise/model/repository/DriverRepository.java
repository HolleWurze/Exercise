package xyz.ITMO.Exercise.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ITMO.Exercise.model.entity.Driver;
import xyz.ITMO.Exercise.model.enums.EnumDriverStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByEmail(String email);
    List<Driver> findAllByStatus(EnumDriverStatus status);
}
