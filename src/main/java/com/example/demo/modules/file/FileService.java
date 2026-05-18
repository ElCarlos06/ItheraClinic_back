package com.example.demo.modules.file;


import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.file.dtos.FileDTO;
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
public class FileService {

    private final FileRepository repository;

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
        ApiResponse response= null;

        File found = repository.findById(id).orElse(null);

        if (found!= null){
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

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public ResponseEntity<ApiResponse> update(FileDTO dto){
        ApiResponse response= null;
        try{
            File found = repository.findById(dto.getId()).orElse(null);

            if (found != null){
                found.setBloodType(dto.getBloodType());
                found.setAllergies(dto.getAllergies());
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

        return  new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public ResponseEntity<ApiResponse> delete(FileDTO dto) {
        ApiResponse response = null;

        try {
            File found = repository.findById(dto.getId()).orElse(null);

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

        return new ResponseEntity<>(response, response.getStatus());
    }


}
