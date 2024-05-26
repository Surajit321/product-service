package com.productservice.ProductService.services;

import com.productservice.ProductService.Repository.OpenSearchProductRepository;
import com.productservice.ProductService.Repository.ProductRepository;
import com.productservice.ProductService.dtos.GenericDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;
import com.productservice.ProductService.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SelfProductServiceImplementation implements ProductService{

    private ProductRepository productRepository;
    private OpenSearchProductRepository openSearchProductRepository;

    public SelfProductServiceImplementation(ProductRepository productRepository,
                                            OpenSearchProductRepository openSearchProductRepository){
        this.productRepository=productRepository;
        this.openSearchProductRepository=openSearchProductRepository;
    }
    @Override
    public GenericDto getProductById(String authToken, long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<GenericDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericDto createProduct(GenericDto genericDto) {
        Product product = new Product();
        product.setTitle(genericDto.getTitle());
        product.setImageUrl(genericDto.getImageUrl());
        product.setDescription(genericDto.getDescription());

        Product savedProduct = this.productRepository.save(product);
        this.productRepository.save(savedProduct);
        return null;
    }

    @Override
    public GenericDto updateProductById(long id, GenericDto genericDto) {
        return null;
    }

    @Override
    public GenericDto deleteProductById(long id) {
        return null;
    }
}
