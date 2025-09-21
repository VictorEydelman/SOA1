package ru.itmo.service2.domain;

import lombok.Data;

@Data
public class Coordinates {
    private Long id;
    private Integer x; //Поле не может быть null
    private float y;
}
