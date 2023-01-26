package xyz.ITMO.Exercise.service;

import xyz.ITMO.Exercise.model.dto.CarsDTORequest;
import xyz.ITMO.Exercise.model.dto.CarsDTOResponse;

public interface CarsService {
    CarsDTORequest create(CarsDTORequest carsDTORequest);

    CarsDTORequest update(CarsDTORequest carsDTORequest);

    CarsDTORequest get(String name);

    void delete(String name);

    CarsDTOResponse addToDriver(String name, String email);
}
