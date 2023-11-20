package ru.umom.umombackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umom.umombackend.models.PhotoEntity;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, String> {
}
