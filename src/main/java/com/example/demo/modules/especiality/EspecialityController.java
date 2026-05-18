package com.example.demo.modules.especiality;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.especiality.dtos.EspecialityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/especiality")
public class EspecialityController {
    private final EspecialityService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> findAll(){
        return service.finAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody EspecialityDTO dto) {
        return service.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> update(@RequestBody EspecialityDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> delete(@RequestBody EspecialityDTO dto) {
        return service.delete(dto);
    }
}
