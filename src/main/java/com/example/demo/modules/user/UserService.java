package com.example.demo.modules.user;

import com.example.demo.kernel.ApiResponse;
import com.example.demo.modules.user.dtos.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll() {
        ApiResponse response = new ApiResponse(
                "Operacion exitosa",
                userRepository.findAll(),
                HttpStatus.OK
        );
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(Long id) {
        ApiResponse response = null;
        User found = userRepository.findById(id).orElse(null);
        if (found != null) {
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

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> save(UserDTO dto) {
        ApiResponse response = null;
        try {
            User user = new User();
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            user.setPhotoUrl(dto.getPhotoUrl());
            User saved = userRepository.save(user);
            response = new ApiResponse(
                    "Operacion exitosa",
                    saved,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            response = new ApiResponse(
                    "Error al guardar",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> update(UserDTO dto) {
        ApiResponse response = null;
        try {
            User found = userRepository.findById(dto.getId()).orElse(null);
            if (found != null) {
                if (dto.getUsername() != null) {
                    found.setUsername(dto.getUsername());
                }
                if (dto.getPassword() != null) {
                    found.setPassword(dto.getPassword());
                }
                if (dto.getPhotoUrl() != null) {
                    found.setPhotoUrl(dto.getPhotoUrl());
                }

                userRepository.saveAndFlush(found);
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
            response = new ApiResponse(
                    "Error al actualizar",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
}