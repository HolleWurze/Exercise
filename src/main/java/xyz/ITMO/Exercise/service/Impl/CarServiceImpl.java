package xyz.ITMO.Exercise.service.Impl;

import xyz.ITMO.Exercise.model.dto.CarsDTORequest;
import xyz.ITMO.Exercise.model.repository.CarsRepository;
import xyz.ITMO.Exercise.service.CarsService;

public class CarServiceImpl implements CarsService {
    @Override
    public CarsDTORequest create(CarsDTORequest carsDTORequest) {
        CarsRepository.findByName(houseDTORequest.getName()).ifPresent(
                h -> {
                    throw new CustomException("Дом с таким названием уже существует", HttpStatus.BAD_REQUEST);
                }
        );

        House house = mapper.convertValue(houseDTORequest, House.class);
        House save = houseRepository.save(house);

        return mapper.convertValue(save, HouseDTORequest.class);
        return null;
    }

    @Override
    public CarsDTORequest update(CarsDTORequest carsDTORequest) {
        return null;
    }

    @Override
    public CarsDTORequest get(String name) {
        return null;
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public CarsDTORequest addToUser(String name, String email) {
        return null;
    }
}
