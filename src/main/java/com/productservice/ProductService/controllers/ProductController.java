package com.productservice.ProductService.controllers;

import com.productservice.ProductService.dtos.GenericDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;
import com.productservice.ProductService.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericDto getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        return this.productService.getProductById(id);
    }

    @GetMapping
    public List<GenericDto> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @PostMapping
    public GenericDto createProduct(@RequestBody GenericDto genericDto) {
        return this.productService.createProduct(genericDto);
    }

    @PutMapping("/{id}")
    public GenericDto updateProductById(@PathVariable("id") long id, @RequestBody GenericDto genericDto) {
        return this.productService.updateProductById(id, genericDto);
    }

    @DeleteMapping("/{id}")
    public GenericDto deleteProductById(@PathVariable("id") long id) {
        return this.productService.deleteProductById(id);
    }

}
