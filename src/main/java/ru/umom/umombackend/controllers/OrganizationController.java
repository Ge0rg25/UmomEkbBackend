package ru.umom.umombackend.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.umom.umombackend.dto.OrganizationDto;
import ru.umom.umombackend.services.OrganizationService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/organizations")
public class OrganizationController {

    OrganizationService organizationService;

    @PutMapping("/create")
    public ResponseEntity<?> create(OrganizationDto.Request.Create dto){
        return organizationService.create(dto);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(OrganizationDto.Request.Update dto){
        return organizationService.update(dto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(OrganizationDto.Request.Delete dto){
        return organizationService.delete(dto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(){
        return organizationService.getAll();
    }

}
