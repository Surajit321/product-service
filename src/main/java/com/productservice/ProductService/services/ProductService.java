package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    GenericDto getProductById(String authToken, long id) throws ProductNotFoundException;
    List<GenericDto> getAllProducts();
    GenericDto createProduct(GenericDto genericDto);
    GenericDto updateProductById(long id, GenericDto genericDto);
    GenericDto deleteProductById(long id);
}
