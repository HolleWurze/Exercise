package xyz.ITMO.Exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import xyz.ITMO.Exercise.model.dto.CarsDTORequest;
import xyz.ITMO.Exercise.model.dto.CarsDTOResponse;

public interface CarsService {
    CarsDTORequest create(CarsDTORequest carsDTORequest);

    CarsDTORequest update(CarsDTORequest carsDTORequest);

    CarsDTORequest get(String name);

    void delete(String name);

    CarsDTOResponse addToDriver(String name, String email);

    ModelMap getAllCars(Integer page, Integer perPage, String sort, Sort.Direction order);
}
