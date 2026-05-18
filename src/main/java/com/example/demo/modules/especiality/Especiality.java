package com.example.demo.modules.especiality;

import com.example.demo.kernel.BaseEntity;
import com.example.demo.modules.doctor.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Especiality extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "especiality", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Doctor> doctors;
}
