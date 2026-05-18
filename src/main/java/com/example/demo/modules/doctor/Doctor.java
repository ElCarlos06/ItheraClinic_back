package com.example.demo.modules.doctor;

import com.example.demo.kernel.BaseEntity;
import com.example.demo.modules.appointment.Appointment;
import com.example.demo.modules.especiality.Especiality;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class Doctor extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastNames;
    @Column(nullable = false, unique = true)
    private String cedula;

    @ManyToOne
    @JoinColumn(name = "id_especiality", nullable = false)
    private Especiality especiality;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments;

}
