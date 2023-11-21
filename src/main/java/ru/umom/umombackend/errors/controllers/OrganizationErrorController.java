package ru.umom.umombackend.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.umom.umombackend.errors.common.OrganizationNotExsitsError;
import ru.umom.umombackend.utils.ErrorUtils;

@RestControllerAdvice
public class OrganizationErrorController {

    @ExceptionHandler(value = {OrganizationNotExsitsError.class})
    public ResponseEntity<?> onNotExists(){
        return ErrorUtils.genereateErrorResponse("organization not exists!", HttpStatus.BAD_REQUEST);
    }

}
