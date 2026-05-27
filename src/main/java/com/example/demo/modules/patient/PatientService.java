package com.example.demo.modules.patient;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.file.File;
import com.example.demo.modules.patient.dtos.PatientDTO;
import com.example.demo.modules.patient.dtos.PatientFileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll(){
        ApiResponse response = new ApiResponse(
                "Operacion exitosa",
                // Invertir el orden de los pacientes para mostrar los más recientes primero
                repository.findAll().reversed(),
                HttpStatus.OK
        );

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(Long id){
        ApiResponse response = null;
        Patient found = repository.findById(id).orElse(null);

        if (found != null){
            response = new ApiResponse("Operacion exitosa",
                    found, HttpStatus.OK);
        } else {
            response = new ApiResponse(
                    "No encontrado",
                    true,
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findByName(String name) {
        ApiResponse response = null;
        List<Patient> patients = repository.findByNameContainingIgnoreCase(name);

        if (patients != null && !patients.isEmpty()){
            response = new ApiResponse("Operacion exitosa",
                    patients, HttpStatus.OK);
        } else {
            response = new ApiResponse(
                    "No encontrado",
                    true,
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public ResponseEntity<ApiResponse> save(PatientDTO dto){
        ApiResponse response = null;

        try {
            Patient p = new Patient();
            p.setName(dto.getName());
            p.setLastNames(dto.getLastNames());
            p.setPhone(dto.getPhone());
            p.setEmail(dto.getEmail());
            p.setBirthDate(dto.getBirthDate());

            PatientFileDTO fileDTO = dto.getFile();
            File file = new File();

            if (fileDTO != null){
                file.setAllergies(fileDTO.getAllergies());
                file.setBloodType(fileDTO.getBloodType());
            }

            p.setFile(file);


            repository.save(p);

            response = new ApiResponse(
                    "Operacion exitosa",
                    null,
                    HttpStatus.OK
            );
        } catch (Exception e){
            log.error("Error al guardar paciente", e);
            response = new ApiResponse(
                    "Error al guardar paciente",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public ResponseEntity<ApiResponse> update(PatientDTO dto){
        ApiResponse response = null;

        try {
            Patient found = repository.findById(dto.getId()).orElse(null);

            if (found != null){

                found.setName(dto.getName());
                found.setLastNames(dto.getLastNames());
                found.setPhone(dto.getPhone());
                found.setEmail(dto.getEmail());
                found.setBirthDate(dto.getBirthDate());

                // Encuentrar el archivo asociado al paciente y actualizarlo
                PatientFileDTO fileDTO = dto.getFile();
                if (fileDTO != null){
                    File file = found.getFile();
                    if (file != null){
                        file.setAllergies(fileDTO.getAllergies());
                        file.setBloodType(fileDTO.getBloodType());
                    } else {
                        // Si el paciente no tenía un archivo, creamos uno nuevo
                        File newFile = new File();
                        newFile.setAllergies(fileDTO.getAllergies());
                        newFile.setBloodType(fileDTO.getBloodType());
                        found.setFile(newFile);
                    }
                }



                repository.saveAndFlush(found);
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
        } catch (Exception e){
            log.error("Error al actualizar paciente", e);
            response = new ApiResponse(
                    "Error al actualizar paciente",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public ResponseEntity<ApiResponse> delete(PatientDTO dto){
        ApiResponse response = null;

        try {
            Patient found = repository.findById(dto.getId()).orElse(null);

            if (found != null){
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
        } catch (Exception e){
            log.error("Error al eliminar paciente", e);
            response = new ApiResponse(
                    "Error al eliminar paciente",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }
}
