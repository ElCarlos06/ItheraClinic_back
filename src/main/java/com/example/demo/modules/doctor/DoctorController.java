package com.example.demo.modules.doctor;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.doctor.dtos.DoctorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService service;

    @GetMapping()
    public ResponseEntity<ApiResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable() Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody DoctorDTO dto){
        return service.save(dto);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> update(@RequestBody DoctorDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable() Long id) {
        return service.delete(id);
    }
}
