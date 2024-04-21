package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FakestoreProductServiceImpl implements ProductService {
    private final RestTemplateBuilder restTemplateBuilder;
    private static final String fakeStoreProductUrl = "https://fakestoreapi.com/products";

    FakestoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericDto getProductById(long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(fakeStoreProductUrl + "/" + id, FakeStoreProductDto.class);

        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product with id " + id + " does not exist.");
        }
        return setUpGenericDto(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public List<GenericDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProducts = responseEntity.getBody();
        List<GenericDto> genericDto = new ArrayList<GenericDto>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProducts) {
            genericDto.add(setUpGenericDto(fakeStoreProductDto));
        }
        return genericDto;
    }

    @Override
    public GenericDto createProduct(GenericDto genericDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(fakeStoreProductUrl, genericDto, FakeStoreProductDto.class);
        return setUpGenericDto(responseEntity.getBody());
    }

    @Override
    public GenericDto updateProductById(long id, GenericDto genericDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(fakeStoreProductUrl + "/" + id, HttpMethod.PUT, requestCallback, responseExtractor, id, genericDto);
        return setUpGenericDto(responseEntity.getBody());
    }

    @Override
    public GenericDto deleteProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(fakeStoreProductUrl + "/" + id, HttpMethod.GET, requestCallback, responseExtractor, id);
        return setUpGenericDto(responseEntity.getBody());
    }

    private GenericDto setUpGenericDto(FakeStoreProductDto fakeStoreProductDto) {
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
