package xyz.ITMO.Exercise.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import xyz.ITMO.Exercise.model.dto.ParkingDTORequest;
import xyz.ITMO.Exercise.service.ParkingService;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor

public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping
    @Operation(summary = "Создание парковки")
    public ResponseEntity<ParkingDTORequest> createParking(@RequestBody ParkingDTORequest parkingDTORequest) {
        return ResponseEntity.ok(parkingService.createParking(parkingDTORequest));
    }

    @PutMapping
    public ResponseEntity<ParkingDTORequest> updateParking(@RequestBody ParkingDTORequest parkingDTORequest) {
        return ResponseEntity.ok(parkingService.updateParking(parkingDTORequest));
    }


    @GetMapping
    public ResponseEntity<ParkingDTORequest> getParking(@RequestParam String name) {
        return ResponseEntity.ok(parkingService.get(name));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteParking(@RequestParam String name) {

        parkingService.deleteParking(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ModelMap getAllCars(@RequestParam(required = false, defaultValue = "1") Integer page,
                               @RequestParam(required = false, defaultValue = "10") Integer perPage,
                               @RequestParam(required = false, defaultValue = "name") String sort,
                               @RequestParam(required = false, defaultValue = "ASC") Sort.Direction order) {
        return parkingService.getAllParking(page, perPage, sort, order);
    }

}
