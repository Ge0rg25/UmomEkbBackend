package ru.umom.umombackend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
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
    int floor;

    @Column
    int workstation;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<OrderEntity> orderEntities = new LinkedHashSet<>();

}
