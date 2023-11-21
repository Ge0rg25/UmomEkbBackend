package ru.umom.umombackend.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.umom.umombackend.errors.common.DishNotExistsError;
import ru.umom.umombackend.utils.ErrorUtils;

@RestControllerAdvice
public class DIshErrorController {


    @ExceptionHandler(value = {DishNotExistsError.class})
    public ResponseEntity<?> onNotExists(){
        return ErrorUtils.genereateErrorResponse("dish not exists!", HttpStatus.BAD_REQUEST);
    }

}
