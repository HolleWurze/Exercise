package xyz.ITMO.Exercise.Jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import xyz.ITMO.Exercise.model.entity.Driver;
import xyz.ITMO.Exercise.model.enums.EnumDriverStatus;
import xyz.ITMO.Exercise.model.repository.DriverRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling
public class DriverJob {

    private final DriverRepository driverRepository;

    @Scheduled(cron = "${timer.duration.value}")
//    @Scheduled(fixedDelay = 5000)
    public void deleteInactiveDrivers() {

//        List<Driver> drivers = driverRepository.findAllByStatus(EnumDriverStatus.DELETED);
//        driverRepository.deleteAll(drivers);

        log.info("Scheduler is working");

    }
}
