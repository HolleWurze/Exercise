package xyz.ITMO.Exercise.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import xyz.ITMO.Exercise.model.enums.EnumCarStatus;
import xyz.ITMO.Exercise.model.enums.EnumParkingStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "parking")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "number_of_parking_space")
    Integer numberOfParkingSpace;

    @Column(name = "working_hours")
    String workingHours;

    @Column(name = "address_parking")
    String addressParking;

    @CreationTimestamp
    @Column(name = "created_date")
    LocalDateTime createdAt;

    @Column(name = "updated_date")
    LocalDateTime updatedAt;

    @Column(name = "deleted_date")
    LocalDateTime deletedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    Car car;

    @Enumerated(EnumType.STRING)
    EnumParkingStatus status = EnumParkingStatus.CREATED;
}
