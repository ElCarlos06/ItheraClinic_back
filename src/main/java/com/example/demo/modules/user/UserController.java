package com.example.demo.modules.user;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.user.dtos.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody UserDTO dto) {
        return service.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> update(@RequestBody UserDTO dto) {
        return service.update(dto);
    }
}