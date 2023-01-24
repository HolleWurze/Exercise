package xyz.ITMO.Exercise.model.dto;

import lombok.Getter;
import lombok.Setter;
import xyz.ITMO.Exercise.model.enums.CarModel;


@Getter
@Setter
public class CarsDTORequest {

    CarModel carModel;
    String yearOfManufacture; // используем string, чтобы исключить проблемы при спуске с фронта даты
    double maximumLoad;

}
