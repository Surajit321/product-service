package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;
import com.productservice.ProductService.security.JWTObject;
import com.productservice.ProductService.security.TokenValidator;
import com.productservice.ProductService.thirdPartyClients.FakeStoreClient;
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
import java.util.Optional;

@Service
public class FakestoreProductServiceImpl implements ProductService {
    private FakeStoreClient fakeStoreClient;
    private TokenValidator tokenValidator;

    public FakestoreProductServiceImpl(FakeStoreClient fakeStoreClient, TokenValidator tokenValidator) {
        this.fakeStoreClient = fakeStoreClient;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public GenericDto getProductById(String authToken, long id) throws ProductNotFoundException {
        Optional<JWTObject> jwtObject = tokenValidator.validateToken(authToken, id);
        if (jwtObject.isEmpty()) {
            return null;
        }
        return setUpGenericDto(this.fakeStoreClient.getProductById(id));
    }

    @Override
    public List<GenericDto> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = this.fakeStoreClient.getAllProducts();
        List<GenericDto> genericDto = new ArrayList<GenericDto>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            genericDto.add(setUpGenericDto(fakeStoreProductDto));
        }
        return genericDto;
    }

    @Override
    public GenericDto createProduct(GenericDto genericDto) {
        return setUpGenericDto(this.fakeStoreClient.createProduct(genericDto));
    }

    @Override
    public GenericDto updateProductById(long id, GenericDto genericDto) {
        return setUpGenericDto(this.fakeStoreClient.updateProductById(id, genericDto));
    }

    @Override
    public GenericDto deleteProductById(long id) {
        return setUpGenericDto(this.fakeStoreClient.deleteProductById(id));
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
