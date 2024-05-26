package com.productservice.ProductService.models;

import com.productservice.ProductService.dtos.GenericDto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends Base {
    private String title;

    @OneToOne(cascade = {jakarta.persistence.CascadeType.REMOVE, jakarta.persistence.CascadeType.PERSIST}, optional = false)
    @JoinColumn(nullable = false, unique = true)
    private Price price;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Category category;

    private String description;

    private String imageUrl;


    public GenericDto from(Product product){
        GenericDto genericDto = new GenericDto();
        genericDto.setTitle(product.getTitle());
        genericDto.setImageUrl(product.getImageUrl());
        genericDto.setDescription(product.getDescription());
        return genericDto;
    }
}
