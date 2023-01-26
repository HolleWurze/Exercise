package xyz.ITMO.Exercise.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import xyz.ITMO.Exercise.model.enums.CarModel;
import xyz.ITMO.Exercise.model.enums.CarStatusEnum;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cars")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Model")
    CarModel carModel;

    @Column(name = "year_of_manufacture")
    String yearOfManufacture;

    @Column(name = "maximum_load")
    Double maximumLoad;

    @CreationTimestamp
    @Column(name = "Created_date")
    LocalDateTime createdAt;

    @Column(name = "Updated_date")
    LocalDateTime updatedAt;

    @Column(name = "Deleted_date")
    LocalDateTime deletedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    Driver driver;

    String name;

    @Enumerated(EnumType.STRING)
    CarStatusEnum status = CarStatusEnum.CREATED;

}
