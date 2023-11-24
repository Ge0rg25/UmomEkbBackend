package ru.umom.umombackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.umom.umombackend.dto.UserDto;
import ru.umom.umombackend.services.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(@AuthenticationPrincipal Jwt jwt){
        return userService.getUserInfo(jwt);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal Jwt jwt, UserDto.Request.Update dto) {
        return userService.update(dto, jwt);
    }


}
