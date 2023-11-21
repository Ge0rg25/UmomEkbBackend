package ru.umom.umombackend.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.umom.umombackend.dto.CatrgoryDto;
import ru.umom.umombackend.errors.common.CategoryNotExistsError;
import ru.umom.umombackend.errors.common.OrganizationNotExsitsError;
import ru.umom.umombackend.models.CategoryEntity;
import ru.umom.umombackend.models.OrganizationEntity;
import ru.umom.umombackend.repositories.CategoryRepository;
import ru.umom.umombackend.repositories.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CatrgoryService {

    CategoryRepository catrgoryRepository;
    OrganizationRepository organizationRepository;


    public ResponseEntity<?> create(CatrgoryDto.Request.Create dto){
        OrganizationEntity organization = organizationRepository.findById(dto.organizationId()).orElseThrow(OrganizationNotExsitsError::new);
        CategoryEntity category = CategoryEntity.builder()
                .title(dto.title())
                .description(dto.description())
                .photoId(dto.photoId())
                .organization(organization)
                .build();
        catrgoryRepository.save(category);

        CatrgoryDto.Response.BaseResponse response = new CatrgoryDto.Response.BaseResponse(
                category.getId(),
                category.getTitle(),
                category.getDescription(),
                category.getPhotoId()
        );
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> update(CatrgoryDto.Request.Update dto) {
        CategoryEntity category = catrgoryRepository.findById(dto.id()).orElseThrow(CategoryNotExistsError::new);
        category.setTitle(dto.title());
        category.setDescription(dto.description());
        category.setPhotoId(dto.photoId());
        catrgoryRepository.save(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> delete(CatrgoryDto.Request.Delete dto) {
        CategoryEntity category = catrgoryRepository.findById(dto.id()).orElseThrow(CategoryNotExistsError::new);
        catrgoryRepository.delete(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getByOrganization(CatrgoryDto.Request.GetByOrganization dto) {
        OrganizationEntity organization = organizationRepository.findById(dto.organizationId()).orElseThrow(OrganizationNotExsitsError::new);
        Set<CategoryEntity> categories = organization.getCategories();
        List<CatrgoryDto.Response.BaseResponse> response = new ArrayList<>();
        for(CategoryEntity category: categories){
            response.add(new CatrgoryDto.Response.BaseResponse(
                    category.getId(),
                    category.getTitle(),
                    category.getDescription(),
                    category.getPhotoId()
            ));
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getAll(){
        List<CategoryEntity> categories = catrgoryRepository.findAll();
        List<CatrgoryDto.Response.BaseResponse> response = new ArrayList<>();
        for(CategoryEntity category: categories){
            response.add(new CatrgoryDto.Response.BaseResponse(
                    category.getId(),
                    category.getTitle(),
                    category.getDescription(),
                    category.getPhotoId()
            ));
        }
        return ResponseEntity.ok(response);
    }


}
