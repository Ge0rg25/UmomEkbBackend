package ru.umom.umombackend.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.umom.umombackend.dto.OrganizationDto;
import ru.umom.umombackend.models.OrganizationEntity;
import ru.umom.umombackend.repositories.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrganizationService {

    OrganizationRepository organizationRepository;


    public ResponseEntity<?> create(OrganizationDto.Request.Create dto) {
        OrganizationEntity organizationEntity = OrganizationEntity.builder()
                .title(dto.title())
                .description(dto.description())
                .address(dto.address())
                .photoId(dto.photoId())
                .build();

        organizationRepository.save(organizationEntity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> update(OrganizationDto.Request.Update dto) {
        OrganizationEntity organizationEntity = organizationRepository.findById(dto.id()).orElseThrow();

        organizationEntity.setTitle(dto.title());
        organizationEntity.setDescription(dto.description());
        organizationEntity.setAddress(dto.address());
        organizationEntity.setPhotoId(dto.photoId());

        organizationRepository.save(organizationEntity);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> delete(OrganizationDto.Request.Delete dto) {
        OrganizationEntity organizationEntity = organizationRepository.findById(dto.id()).orElseThrow();
        organizationRepository.delete(organizationEntity);

        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> getAll(){
        List<OrganizationEntity> organizations = organizationRepository.findAll();
        List<OrganizationDto.Response.BaseResponse> responses = new ArrayList<>();

        for(OrganizationEntity organizationEntity: organizations ){
            responses.add(
                    entityToBaseResponse(organizationEntity)
            );
        }

        return ResponseEntity.ok(responses);
    }


    private OrganizationDto.Response.BaseResponse entityToBaseResponse(OrganizationEntity organization){
       return new OrganizationDto.Response.BaseResponse(
               organization.getId(),
               organization.getTitle(),
               organization.getDescription(),
               organization.getAddress(),
               organization.getPhotoId()
       );
    }

}
