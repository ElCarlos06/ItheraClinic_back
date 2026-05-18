package com.example.demo.modules.doctor;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.doctor.dtos.DoctorDTO;
import com.example.demo.modules.especiality.Especiality;
import com.example.demo.modules.especiality.EspecialityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorRepository repository;
    private final EspecialityRepository especialityRepository;

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

        Doctor found = repository.findById(id).orElse(null);

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

    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<ApiResponse> save(DoctorDTO dto){
        ApiResponse response = null;
        try {
            Doctor doctor = new Doctor();
            doctor.setName(dto.getName());
            doctor.setLastNames(dto.getLastNames());
            doctor.setCedula(dto.getCedula());

            Especiality especiality = especialityRepository.findById(dto.getIdEspeciality()).orElse(null);

            if (especiality != null) {
                doctor.setEspeciality(especiality);
            } else {
                response = new ApiResponse(
                        "Especialidad no encontrada",
                        true,
                        HttpStatus.BAD_REQUEST
                );
                return new ResponseEntity<>(response, response.getStatus());
            }

            Doctor saved = repository.save(doctor);

            response = new ApiResponse(
                    "Operacion exitosa",
                    saved,
                    HttpStatus.OK
            );
        } catch (Exception e){
            log.error("Error al guardar el doctor", e);
            response = new ApiResponse(
                    "Error al guardar el doctor",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    public ResponseEntity<ApiResponse> update(DoctorDTO dto) {
        ApiResponse response = null;

        try {
            Doctor found = repository.findById(dto.getId()).orElse(null);

            if (found != null) {
                found.setName(dto.getName());
                found.setLastNames(dto.getLastNames());
                found.setCedula(dto.getCedula());

                if (dto.getIdEspeciality() != null) {
                    found.getEspeciality().setId(dto.getIdEspeciality());
                }
                Doctor updated = repository.save(found);
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
            log.error("Error al actualizar el doctor", e);
            response = new ApiResponse(
                    "Error al actualizar el doctor",
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
            Doctor found = repository.findById(id).orElse(null);

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
            log.error("Error al eliminar el doctor", e);
            response = new ApiResponse(
                    "Error al eliminar el doctor",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }
}
