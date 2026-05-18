package com.example.demo.modules.appointment;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.appointment.dtos.AppointmentDTO;
import com.example.demo.modules.doctor.DoctorRepository;
import com.example.demo.modules.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepository repository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll(){
        ApiResponse response = new ApiResponse(
                "Operacion exitosa",
                repository.findAll(),
                HttpStatus.OK
        );

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(Long id){
        ApiResponse response = null;

        Appointment found = repository.findById(id).orElse(null);

        if (found!=null){
            response = new ApiResponse(
                    "Operacion exitosa",
                    found,
                    HttpStatus.OK
            );
        } else {
            response = new ApiResponse(
                    "No encontrado",
                    true,
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional
    public ResponseEntity<ApiResponse> save(AppointmentDTO dto){
        ApiResponse response = null;
        try {
            Appointment appointment = new Appointment();
            appointment.setDate(dto.getDate());
            appointment.setTime(dto.getTime());
            appointment.setReason(dto.getReason());
            appointment.setDoctor(doctorRepository.findById(dto.getIdDoctor()).orElse(null));
            appointment.setPatient(patientRepository.findById(dto.getIdPatient()).orElse(null));

            if (dto.getStatus() == null || dto.getStatus().isEmpty()) {
                appointment.setStatus("PENDIENTE");
            } else {
                appointment.setStatus(dto.getStatus());
            }

            repository.save(appointment);

            response = new ApiResponse(
                    "Operacion exitosa",
                    appointment,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            log.error("Error al guardar la cita", e);
            response = new ApiResponse(
                    "Error al guardar la cita",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<ApiResponse> update(AppointmentDTO dto) {
        ApiResponse response = null;

        try {
            Appointment found = repository.findById(dto.getId()).orElse(null);

            if (found != null) {
                found.setDate(dto.getDate());
                found.setTime(dto.getTime());
                found.setReason(dto.getReason());
                found.setStatus(dto.getStatus());
                if (dto.getIdDoctor() != null) {
                    found.setDoctor(doctorRepository.findById(dto.getIdDoctor()).orElse(null));
                }
                if (dto.getIdPatient() != null) {
                    found.setPatient(patientRepository.findById(dto.getIdPatient()).orElse(null));
                }
                Appointment updated = repository.save(found);
                response = new ApiResponse(
                        "Operacion exitosa",
                        updated,
                        HttpStatus.OK
                );
            } else {
                response = new ApiResponse(
                        "No encontrado",
                        true,
                        HttpStatus.NOT_FOUND
                );
            }
        } catch (Exception e) {
            log.error("Error al actualizar la cita", e);
            response = new ApiResponse(
                    "Error al actualizar la cita",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<ApiResponse> delete(Long id) {
        ApiResponse response = null;

        try {
            Appointment found = repository.findById(id).orElse(null);

            if (found != null) {
                repository.deleteById(found.getId());
                response = new ApiResponse(
                        "Operacion exitosa",
                        null,
                        HttpStatus.OK
                );
            } else {
                response = new ApiResponse(
                        "No encontrado",
                        true,
                        HttpStatus.NOT_FOUND
                );
            }
        } catch (Exception e) {
            log.error("Error al eliminar la cita", e);
            response = new ApiResponse(
                    "Error al eliminar la cita",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }
}
