package xyz.ITMO.Exercise.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.ITMO.Exercise.model.dto.DriverDTORequest;
import xyz.ITMO.Exercise.service.DriverService;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public DriverDTORequest createDriver(@RequestBody DriverDTORequest driverDTORequest) {
        return driverService.createDriver(driverDTORequest);
    }

    @PutMapping
    public ResponseEntity<DriverDTORequest> updateCar(@RequestBody DriverDTORequest driverDTORequest) {
        return ResponseEntity.ok(driverService.updateDriver(driverDTORequest));
    }

    @GetMapping
    public ResponseEntity<DriverDTORequest> getCar(@RequestParam String email) {
        return ResponseEntity.ok(driverService.get(email));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteCar(@RequestParam String email) {

        driverService.deleteDriver(email);
        return ResponseEntity.ok().build();
    }
}
