package xyz.ITMO.Exercise.model.dto;


import lombok.Getter;
import lombok.Setter;
import xyz.ITMO.Exercise.model.enums.ParkingAddresses;

@Getter
@Setter
public class ParkingDTORequest {

    ParkingAddresses parkingAddresses;
    Integer numberOfParkingSpace;
    String workingHours;
    String addressParking;

}
