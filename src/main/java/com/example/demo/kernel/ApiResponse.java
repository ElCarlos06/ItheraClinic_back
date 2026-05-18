package com.example.demo.kernel;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private String message;
    private Object data;
    private boolean error;
    private HttpStatus status;

    //Mensajes de exito sin playlod
    public ApiResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
    //mensaje de exito con playlod
    public ApiResponse(String message, Object data, HttpStatus status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }
    //Mensaje de error sin playlod(retroalimentacion)
    public ApiResponse(String message, boolean error, HttpStatus status) {
        this.message = message;
        this.error = error;
        this.status = status;
    }
    //Mensaje de error con paylod
    public ApiResponse(String message, Object data, boolean error, HttpStatus status) {
        this.message = message;
        this.data = data;
        this.error = error;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }
}
