package ru.umom.umombackend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "CATEGORIES")
public class CategoryEtity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;

    String description;

    String photoId;

}
