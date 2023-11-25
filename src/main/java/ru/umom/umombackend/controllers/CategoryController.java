package ru.umom.umombackend.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.umom.umombackend.dto.CatrgoryDto;
import ru.umom.umombackend.services.CatrgoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryController {

    CatrgoryService catrgoryService;


    @PutMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated CatrgoryDto.Request.Create dto){
        return catrgoryService.create(dto);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Validated CatrgoryDto.Request.Update dto){
        return catrgoryService.update(dto);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody @Validated CatrgoryDto.Request.Delete dto){
        return catrgoryService.delete(dto);
    }


    @PostMapping("/get/by/organization")
    public ResponseEntity<?> getByOrganization(@RequestBody @Validated CatrgoryDto.Request.GetByOrganization dto){
        return catrgoryService.getByOrganization(dto);
    }

    @GetMapping("/get/all")
    public List<CatrgoryDto.Response.BaseResponse> getAll(){
        return catrgoryService.getAll();
    }

}
