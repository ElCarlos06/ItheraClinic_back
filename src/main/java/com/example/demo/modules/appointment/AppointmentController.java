package com.example.demo.modules.appointment;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.appointment.dtos.AppointmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable() Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody AppointmentDTO dto){
        return service.save(dto);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> update(@RequestBody AppointmentDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable() Long id) {
        return service.delete(id);
    }
}
