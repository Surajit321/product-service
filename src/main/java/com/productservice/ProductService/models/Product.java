package com.productservice.ProductService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends Base {
    private String title;
    private int price;
    @ManyToOne
    private Category category;
    private String description;
    private String imageUrl;
}
