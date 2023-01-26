package xyz.ITMO.Exercise.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.ITMO.Exercise.model.dto.CarsDTORequest;
import xyz.ITMO.Exercise.model.dto.CarsDTOResponse;
import xyz.ITMO.Exercise.service.CarsService;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarsService carsService;

    @PostMapping
    public ResponseEntity<CarsDTORequest> createCar(@RequestBody CarsDTORequest carsDTORequest) {
        return ResponseEntity.ok(carsService.create(carsDTORequest));
    }

    @PutMapping
    public ResponseEntity<CarsDTORequest> updateCar(@RequestBody CarsDTORequest carsDTORequest) {
        return ResponseEntity.ok(carsService.update(carsDTORequest));
    }


    @GetMapping
    public ResponseEntity<CarsDTORequest> getCar(@RequestParam String name) {
        return ResponseEntity.ok(carsService.get(name));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteCar(@RequestParam String name) {

        carsService.delete(name);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/carOwner")
    public ResponseEntity<CarsDTOResponse> addToDriver(@RequestParam String name, @RequestParam String email) {
        return ResponseEntity.ok(carsService.addToDriver(name, email));
    }
}
