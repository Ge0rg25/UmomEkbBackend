package ru.umom.umombackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umom.umombackend.models.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
