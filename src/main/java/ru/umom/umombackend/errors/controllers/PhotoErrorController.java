package ru.umom.umombackend.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.umom.umombackend.errors.common.ContentTypeNotAllowedError;
import ru.umom.umombackend.errors.common.PhotoNotExistsError;
import ru.umom.umombackend.utils.ErrorUtils;

@RestControllerAdvice
public class PhotoErrorController {


    @ExceptionHandler(value = {ContentTypeNotAllowedError.class})
    public ResponseEntity<?> contentTypeNotAllowed(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("File extention not allowed. Please, use .jpg or .png", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PhotoNotExistsError.class})
    public ResponseEntity<?> photoNotExists(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("Photo not exist", HttpStatus.NOT_FOUND);
    }
}
