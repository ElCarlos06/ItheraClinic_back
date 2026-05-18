package com.example.demo.modules.file;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.file.dtos.FileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> findAll() {
        return service.findAll();
    }

    /*
    @GetMapping("/by-patient")
    public ResponseEntity<ApiResponse> findByPatientId(Long patientId){
        return service.findByPatientId(patientId);

    }
     */

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> finById(@PathVariable  Long id){
        return service.findById(id);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> update(@RequestBody FileDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> delete(@RequestBody FileDTO dto) {
        return service.delete(dto);
    }
}