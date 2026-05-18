package com.example.demo.modules.file;

import com.example.demo.kernel.BaseEntity;
import com.example.demo.modules.patient.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class File extends BaseEntity {
    @Column
    private String bloodType;
    @Column
    private String allergies;

    @OneToOne(mappedBy = "file")
    @JsonIgnore
    private Patient patient;
}
