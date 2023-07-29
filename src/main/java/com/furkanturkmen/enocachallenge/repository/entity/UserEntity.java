package com.furkanturkmen.enocachallenge.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document("User_Entity")
public class UserEntity extends BaseEntity {
    @Id
    private String id;
    private String authId;
    private String name;
    private String surname;
}
