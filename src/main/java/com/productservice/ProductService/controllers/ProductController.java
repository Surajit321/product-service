package com.productservice.ProductService.controllers;

import com.productservice.ProductService.dtos.FakeStoreProductDto;
import com.productservice.ProductService.dtos.GenericDto;
import com.productservice.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericDto getProductById(@PathVariable("id") long id)
    {
        return this.productService.getProductById(id);
    }

    @GetMapping
    public void getAllProducts()
    {

    }

    @PutMapping("/{id}")
    public void updateProductById(@PathVariable("id") long id)
    {

    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") long id)
    {

    }
}
