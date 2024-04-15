package com.productservice.ProductService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericDto {
    private long id;
    private String title;
    private int price;
    private String category;
    private String description;
    private String imageUrl;
}
