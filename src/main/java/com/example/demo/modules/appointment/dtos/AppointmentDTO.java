package com.example.demo.modules.appointment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {
    private Long id;
    private String date;
    private String time;
    private String reason;
    private String status;
    private Long idDoctor;
    private Long idPatient;

}
