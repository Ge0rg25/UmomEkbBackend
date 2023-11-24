package ru.umom.umombackend.services;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.umom.umombackend.dto.DishDto;
import ru.umom.umombackend.errors.common.CategoryNotExistsError;
import ru.umom.umombackend.errors.common.DishNotExistsError;
import ru.umom.umombackend.models.CategoryEntity;
import ru.umom.umombackend.models.DishEntity;
import ru.umom.umombackend.repositories.CategoryRepository;
import ru.umom.umombackend.repositories.DishRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DishService {

    DishRepository dishRepository;
    CategoryRepository categoryRepository;



    public ResponseEntity<?> create(DishDto.Request.Create dto){
        CategoryEntity category = categoryRepository.findById(dto.categoryId()).orElseThrow(CategoryNotExistsError::new);
        DishEntity dish = DishEntity.builder()
                .title(dto.title())
                .description(dto.description())
                .price(dto.price())
                .cpfc(dto.cpfc())
                .category(category)
                .build();

        dishRepository.save(dish);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> update(DishDto.Request.Update dto){
        DishEntity dish = dishRepository.findById(dto.id()).orElseThrow(DishNotExistsError::new);
        dish.setTitle(dto.title());
        dish.setDescription(dto.description());
        dish.setPrice(dto.price());
        dish.setCpfc(dto.cpfc());
        dishRepository.save(dish);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> delete(DishDto.Request.Delete dto){
        DishEntity dish = dishRepository.findById(dto.id()).orElseThrow(DishNotExistsError::new);
        dishRepository.delete(dish);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> getAll(){
        List<DishEntity> dishes = dishRepository.findAll();
        List<DishDto.Response.Dish> response = new ArrayList<>();
        for(DishEntity dish: dishes){
            response.add(entityToBaseResponse(dish));
        }
        return ResponseEntity.ok(response);

    }

    public ResponseEntity<?> getByCategory(DishDto.Request.GetByCategory dto){
        CategoryEntity category = categoryRepository.findById(dto.categoryId()).orElseThrow(CategoryNotExistsError::new);
        Set<DishEntity> dishes = category.getDishes();
        List<DishDto.Response.Dish> response = new ArrayList<>();
        for(DishEntity dish: dishes){
            response.add(entityToBaseResponse(dish));
        }
        return ResponseEntity.ok(response);

    }

    private DishDto.Response.Dish entityToBaseResponse(DishEntity dish){
        return new DishDto.Response.Dish(
                dish.getId(),
                dish.getTitle(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getCpfc(),
                dish.getCategory().getId()
        );
    }

}
