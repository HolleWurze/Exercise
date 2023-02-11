package xyz.ITMO.Exercise.service;

import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;
import xyz.ITMO.Exercise.model.dto.ParkingDTORequest;

public interface ParkingService {

    ParkingDTORequest createParking(ParkingDTORequest parkingDTORequest);

    ParkingDTORequest updateParking(ParkingDTORequest parkingDTORequest);

    ParkingDTORequest get(String name);

    void deleteParking(String name);

    ModelMap getAllParking(Integer page, Integer perPage, String sort, Sort.Direction order);

}
