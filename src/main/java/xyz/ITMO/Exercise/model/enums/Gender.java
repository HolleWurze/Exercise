package xyz.ITMO.Exercise.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("Мужчина"),
    FEMALE("Женщина");

    private final String description;
}
