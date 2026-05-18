package com.example.demo.modules.patient.dtos;


import com.example.demo.modules.file.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private String lastNames;
    private Long phone;
    private String email;
    private String birthDate;
    private PatientFileDTO file;
}
