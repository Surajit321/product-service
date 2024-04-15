package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class FakestoreProductServiceImpl implements ProductService{
    private final RestTemplateBuilder restTemplateBuilder;
    private static final String fakeStoreProductUrl = "https://fakestoreapi.com/products/1";

    FakestoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericDto getProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductDto.class);
        return setUpGenericDto(Objects.requireNonNull(responseEntity.getBody()));
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

    private GenericDto setUpGenericDto(FakeStoreProductDto fakeStoreProductDto)
    {
        GenericDto genericDto = new GenericDto();
        genericDto.setId(fakeStoreProductDto.getId());
        genericDto.setDescription(fakeStoreProductDto.getDescription());
        genericDto.setCategory(fakeStoreProductDto.getCategory());
        genericDto.setTitle(fakeStoreProductDto.getTitle());
        genericDto.setPrice(fakeStoreProductDto.getPrice());
        genericDto.setImageUrl(fakeStoreProductDto.getImageUrl());
        return genericDto;
    }
}
