package com.productservice.ProductService.security;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class JWTObject {
    private String email;
    private Long userId;
    private LocalDate expiryDate;
    private LocalDate createdAt;
    private List<Role> roles;
}
