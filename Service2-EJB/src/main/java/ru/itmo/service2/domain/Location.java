package ru.itmo.service2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {
    private Long id;
    private long x;
    private Long y; //Поле не может быть null
    private long z;
    private String name; //Длина строки не должна быть больше 588, Поле может быть null
}
