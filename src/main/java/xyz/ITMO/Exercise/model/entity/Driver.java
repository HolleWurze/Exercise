package xyz.ITMO.Exercise.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import xyz.ITMO.Exercise.model.enums.EnumDriverStatus;
import xyz.ITMO.Exercise.model.enums.Gender;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "drivers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer age;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(unique = true)
    String email;

    @Enumerated(EnumType.STRING) //пропись не 0 и 1, а именно string
    Gender gender;

    @CreationTimestamp
    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    List<Car> cars;

    @Enumerated(EnumType.STRING)
    EnumDriverStatus status = EnumDriverStatus.CREATED;

}
