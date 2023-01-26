package xyz.ITMO.Exercise.service.Status;

import xyz.ITMO.Exercise.model.dto.CarsDTORequest;
import xyz.ITMO.Exercise.model.dto.CarsDTOResponse;

public interface CarsStatus {

    CarsDTORequest create(CarsDTORequest carsDTORequest);

    CarsDTORequest update(CarsDTORequest carsDTORequest);

    CarsDTORequest get(String name);

    void delete(String name);

    CarsDTOResponse addToDriver(String name, String email);
}
