package com.example.demo.modules.doctor.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorDTO {
    private Long id;
    private String name;
    private String lastNames;
    private String cedula;
    private Long idEspeciality;
}
