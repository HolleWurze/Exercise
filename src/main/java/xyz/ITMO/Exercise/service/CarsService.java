package xyz.ITMO.Exercise.service;

import xyz.ITMO.Exercise.model.dto.CarsDTORequest;

public interface CarsService {
    CarsDTORequest create(CarsDTORequest carsDTORequest);

    CarsDTORequest update(CarsDTORequest carsDTORequest);

    CarsDTORequest get(String name);

    void delete(String name);

    CarsDTORequest addToUser(String name, String email);
}
