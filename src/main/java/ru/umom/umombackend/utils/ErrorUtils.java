package ru.umom.smolathonbackend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ErrorUtils {

    static public ResponseEntity<?> genereateErrorResponse(String message, HttpStatus status){
        return new ResponseEntity<>(Map.of("error", message), status);
    }

}