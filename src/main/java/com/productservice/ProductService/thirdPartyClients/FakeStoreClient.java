package com.productservice.ProductService.thirdPartyClients;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class FakeStoreClient {
    private final RestTemplateBuilder restTemplateBuilder;
    private String fakeStoreProductUrl;

    FakeStoreClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakeStore.api.product.baseUrl}") String fakeStoreProductUrl) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreProductUrl = fakeStoreProductUrl;
    }

    public FakeStoreProductDto getProductById(long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(this.fakeStoreProductUrl + "/" + id, FakeStoreProductDto.class);

        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product with id " + id + " does not exist.");
        }
        return Objects.requireNonNull(responseEntity.getBody());
    }

    public FakeStoreProductDto[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(this.fakeStoreProductUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProducts = responseEntity.getBody();
        return fakeStoreProducts;
    }

    public FakeStoreProductDto createProduct(GenericDto genericDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(this.fakeStoreProductUrl, genericDto, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }

    public FakeStoreProductDto updateProductById(long id, GenericDto genericDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(this.fakeStoreProductUrl + "/" + id, HttpMethod.PUT, requestCallback, responseExtractor, id, genericDto);
        return responseEntity.getBody();
    }

    public FakeStoreProductDto deleteProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(this.fakeStoreProductUrl + "/" + id, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return responseEntity.getBody();
    }
}
