package ru.umom.umombackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umom.umombackend.models.DishEntity;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, String> {
}
