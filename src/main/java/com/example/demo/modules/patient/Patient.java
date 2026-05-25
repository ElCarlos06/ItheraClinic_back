package com.example.demo.modules.patient;

import com.example.demo.kernel.BaseEntity;
import com.example.demo.modules.file.File;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends BaseEntity {
    @Column (nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastNames;
    @Column(unique = true)
    private Long phone;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_file", nullable = false)
    private File file;


}
