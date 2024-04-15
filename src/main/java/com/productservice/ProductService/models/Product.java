package com.productservice.ProductService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends Base {
    private String title;
    private int price;
    private Category category;
    private String description;
    private String imageUrl;
}
