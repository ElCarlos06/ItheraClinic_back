package com.example.demo.modules.especiality;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.especiality.dtos.EspecialityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EspecialityService {
    private final EspecialityRepository repository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> finAll(){
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

        Especiality found = repository.findById(id).orElse(null);

        if (found != null){
            response = new ApiResponse(
                    "Operacion exitosa",
                    found,
                    HttpStatus.OK
            );
        }else {
            response = new ApiResponse(
                    "No encontrado",
                    true,
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public ResponseEntity<ApiResponse> save(EspecialityDTO dto){
        ApiResponse response = null;

        try{
            Especiality especiality = new Especiality();

            especiality.setName(dto.getName());
            especiality.setDescription(dto.getDescription());

            repository.save(especiality);

            response = new ApiResponse(
                    "Operacion exitosa",
                    especiality,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            response = new ApiResponse(
                    "Error al guardar",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

            e.printStackTrace();
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public ResponseEntity<ApiResponse> update(EspecialityDTO dto){
        ApiResponse response = null;

        try{
            Especiality found = repository.findById(dto.getId()).orElse(null);

            if (found != null){
                found.setName(dto.getName());
                found.setDescription(dto.getDescription());

                repository.save(found);

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
        } catch (Exception e) {
            e.printStackTrace();
            response = new ApiResponse(
                    "Error al actualizar",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public ResponseEntity<ApiResponse> delete(EspecialityDTO dto) {
        ApiResponse response = null;

        try {
            Especiality found = repository.findById(dto.getId()).orElse(null);

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
            e.printStackTrace();
            response = new ApiResponse(
                    "Error al eliminar",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return  new ResponseEntity<>(response, response.getStatus());
    }
}
