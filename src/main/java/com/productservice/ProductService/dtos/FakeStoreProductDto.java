package com.productservice.ProductService.dtos;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreProductDto implements Serializable {
    private long id;
    private String title;
    private int price;
    private String category;
    private String description;
    private String imageUrl;
}
