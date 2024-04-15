package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.GenericDto;

public interface ProductService {
    GenericDto getProductById(long id);
    void getAllProducts();
    void updateProductById(long id);
    void deleteProductById(long id);
}
