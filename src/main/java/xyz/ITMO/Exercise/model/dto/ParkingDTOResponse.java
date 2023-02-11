package xyz.ITMO.Exercise.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ParkingDTOResponse extends ParkingDTORequest{

    CarsDTORequest carsDTORequest;
}
