package com.example.demo.modules.patient;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.patient.dtos.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody PatientDTO dto){
        return service.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> update(@RequestBody PatientDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> delete(@RequestBody PatientDTO dto){
        return service.delete(dto);
    }
}
