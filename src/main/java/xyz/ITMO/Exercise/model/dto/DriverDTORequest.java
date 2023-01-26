package xyz.ITMO.Exercise.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import xyz.ITMO.Exercise.model.enums.Gender;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverDTORequest {

    Integer age;
    String firstName;
    String lastName;
    Gender gender;
    String email;


}
