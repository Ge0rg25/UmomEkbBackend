package ru.umom.umombackend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "ORDERS")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column
    boolean delivery;



    @CreationTimestamp
    Timestamp createdAt;

    @UpdateTimestamp
    Timestamp updatedAt;

    @Column
    boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany
    @JoinTable(name = "ORDERS_DISHS",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<DishEntity> dishes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

}
