package ru.umom.umombackend.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.umom.umombackend.dto.DishDto;
import ru.umom.umombackend.services.DishService;

@RestController
@RequestMapping("/dishes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DishController {

    DishService dishService;

    @PutMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated DishDto.Request.Create dto){
        return dishService.create(dto);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Validated DishDto.Request.Update dto){
        return dishService.update(dto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody @Validated DishDto.Request.Delete dto){
        return dishService.delete(dto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(){
        return dishService.getAll();
    }

    @PostMapping("/get/by/category")
    public ResponseEntity<?> getByCategory(@RequestBody @Validated DishDto.Request.GetByCategory dto){
        return dishService.getByCategory(dto);
    }

    // F off
    @PostMapping("/get/by/id")
    public DishDto.Response.Dish getDishById(@RequestBody @Validated DishDto.Request.GetById dto){
        return dishService.getDishById(dto);
    }

}
