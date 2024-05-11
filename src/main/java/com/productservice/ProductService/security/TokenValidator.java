package com.productservice.ProductService.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class TokenValidator {

    private RestTemplateBuilder restTemplateBuilder;

    TokenValidator(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    /*
    *This method should call the validate method of the user-service
    * if the token is valid, it should return corresponding object class else
    * it should return an empty object
     */
    public Optional<JWTObject> validateToken(String authToken, Long userId) {
        ValidateTokenDto validateTokenDto = new ValidateTokenDto();
        validateTokenDto.setToken(authToken);
        validateTokenDto.setUserId(userId);
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        return Optional.ofNullable(restTemplate.postForEntity("http://localhost:8080/auth/validate", validateTokenDto, JWTObject.class).getBody());
    }
}
