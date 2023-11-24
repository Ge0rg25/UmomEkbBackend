package ru.umom.umombackend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "ORGANIZATIONS")
public class OrganizationEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @OneToMany(mappedBy = "organization", orphanRemoval = true)
    Set<CategoryEntity> categories = new LinkedHashSet<>();

    @Column
    String title;

    @Column
    String description;

    @Column
    String address;

    @Column
    String photoId;


    @CreationTimestamp
    Timestamp createdAt;

    @UpdateTimestamp
    Timestamp updatedAt;

    @OneToMany(mappedBy = "organization", orphanRemoval = true)
    private Set<OrderEntity> orders = new LinkedHashSet<>();

}
