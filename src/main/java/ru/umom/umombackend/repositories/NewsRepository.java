package ru.umom.umombackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umom.umombackend.models.NewsEntity;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, String > {
}
