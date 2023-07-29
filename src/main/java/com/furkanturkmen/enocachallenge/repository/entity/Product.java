package com.furkanturkmen.enocachallenge.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "Product")
public class Product extends BaseEntity {
    @Id
    private String id;
    private String storeId;
    private String name;
    private String description;
    private String price;
    private String category;
    private int stock;
}
