package ru.car.washing.dto;

import lombok.Data;
import ru.car.washing.model.Box;

import java.util.List;

@Data
public class PersonDTO {
    private String name;
    private List<Box> reservedBoxes;
}
