package com.productservice.ProductService.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenDto {
    private Long userId;
    private String token;
}
