package com.example.demo.modules.especiality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecialityDTO {
    private Long id;
    private String name;
    private String description;
}
