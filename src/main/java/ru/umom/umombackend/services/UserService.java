package ru.umom.umombackend.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.umom.umombackend.dto.UserDto;
import ru.umom.umombackend.models.UserEntity;
import ru.umom.umombackend.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> update(UserDto.Request.Update dto, Jwt jwt){
        UserEntity user = userRepository.findById(jwt.getSubject()).orElse(UserEntity.builder()
                .id(jwt.getSubject())
                .name(jwt.getClaim("name"))
                .build());

        user.setName(dto.name());
        user.setFloor(dto.floor());
        user.setWorkstation(dto.workstation());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> getUserInfo(Jwt jwt){
        UserEntity user = userRepository.findById(jwt.getSubject()).orElse(UserEntity.builder()
                .id(jwt.getSubject())
                .name(jwt.getClaim("name"))
                .build());
        UserDto.Response.User response = new UserDto.Response.User(user.getId(), user.getName(), user.getFloor(), user.getWorkstation());
        return ResponseEntity.ok(response);
    }

}
