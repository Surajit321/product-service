package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductService {
    FakeStoreProductDto getProductById(long id);
    void getAllProducts();
    void updateProductById(long id);
    void deleteProductById(long id);
}
