package ru.umom.umombackend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "USERS")
public class UserEntity {

    @Id
    String id;

    @Column
    String name;


    @Column
    short floor;

    @Column
    short workstation;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();


    @CreationTimestamp
    Timestamp createdAt;

    @UpdateTimestamp
    Timestamp updatedAt;

}
