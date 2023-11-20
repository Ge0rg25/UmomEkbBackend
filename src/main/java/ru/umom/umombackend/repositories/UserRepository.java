package ru.umom.umombackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umom.umombackend.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
