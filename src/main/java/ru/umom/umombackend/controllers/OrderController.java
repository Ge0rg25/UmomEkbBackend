package ru.umom.umombackend.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.umom.umombackend.dto.OrderDto;
import ru.umom.umombackend.services.OrderService;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    OrderService orderService;

    @PutMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated OrderDto.Request.Create dto){
        return orderService.create(jwt, dto);
    }

    @PatchMapping("/complete")
    public ResponseEntity<?> delete(@RequestBody @Validated OrderDto.Request.Complete dto){
        return orderService.complete(dto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal Jwt jwt) {
        return orderService.getAll(jwt);
    }


    @GetMapping("/get/by/organization")
    public ResponseEntity<?> getAllByOrganization(@RequestBody @Validated OrderDto.Request.GetAllByOrganization dto){
        return orderService.getAllByOrganization(dto);
    }


}
