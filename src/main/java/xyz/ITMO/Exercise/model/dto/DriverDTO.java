package xyz.ITMO.Exercise.model.dto;

import lombok.Getter;
import lombok.Setter;
import xyz.ITMO.Exercise.model.enums.Gender;

import java.util.List;

@Getter
@Setter
public class DriverDTO {

    Integer age;
    String firstName;
    String lastName;
    Gender gender;
    List<CarsDTORequest> cars;

}
