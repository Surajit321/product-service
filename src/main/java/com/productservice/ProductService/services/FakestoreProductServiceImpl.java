package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FakestoreProductServiceImpl implements ProductService{
    private final RestTemplateBuilder restTemplateBuilder;
    private static final String fakeStoreProductUrl = "https://fakestoreapi.com/products";

    FakestoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericDto getProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(fakeStoreProductUrl + "/" + id, FakeStoreProductDto.class);
        return setUpGenericDto(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public List<GenericDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProducts = responseEntity.getBody();
        List<GenericDto> genericDto = new ArrayList<GenericDto>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProducts)
        {
            genericDto.add(setUpGenericDto(fakeStoreProductDto));
        }
        return genericDto;
    }

    @Override
    public FakeStoreProductDto createProduct(GenericDto genericDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(fakeStoreProductUrl, genericDto, FakeStoreProductDto.class);
        return responseEntity.getBody();
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
