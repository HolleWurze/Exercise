package xyz.ITMO.Exercise.Controllers;

import lombok.RequiredArgsConstructor;
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
}
