package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.GenericDto;

import java.util.List;

public interface ProductService {
    GenericDto getProductById(long id);
    List<GenericDto> getAllProducts();
    void updateProductById(long id);
    void deleteProductById(long id);
}
