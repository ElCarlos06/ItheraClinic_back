package com.example.demo.modules.patient;

import com.example.demo.kernel.BaseEntity;
import com.example.demo.modules.file.File;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private String birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_file", nullable = false)
    private File file;


}
