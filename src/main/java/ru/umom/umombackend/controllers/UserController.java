package ru.umom.umombackend.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.umom.umombackend.dto.UserDto;
import ru.umom.umombackend.services.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(@AuthenticationPrincipal Jwt jwt){
        return userService.getUserInfo(jwt);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated UserDto.Request.Update dto) {
        return userService.update(dto, jwt);
    }


}
