package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakestoreProductServiceImpl implements ProductService{
    private final RestTemplateBuilder restTemplateBuilder;
    private static final String fakeStoreProductUrl = "https://fakestoreapi.com/products/1";

    FakestoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public FakeStoreProductDto getProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }

    @Override
    public void getAllProducts() {

    }

    @Override
    public void updateProductById(long id) {

    }

    @Override
    public void deleteProductById(long id) {

    }
}
