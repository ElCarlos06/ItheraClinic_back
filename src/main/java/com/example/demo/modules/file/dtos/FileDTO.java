package com.example.demo.modules.file.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private  Long id;
    private String bloodType;
    private String allergies;
}
