package com.productservice.ProductService.controllers;

import com.productservice.ProductService.dtos.GenericDto;
import com.productservice.ProductService.dtos.SearchRequestDto;
import com.productservice.ProductService.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

//    @PostMapping
//    public List<GenericDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto){
//        return searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getItemsPerPage());
//    }

    @PostMapping
    public Page<GenericDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto){
        List<GenericDto> genericDtoList = searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getItemsPerPage(), searchRequestDto.getSortParams());
        Page<GenericDto> genericDtoPage = new PageImpl(
                genericDtoList
        );
        return genericDtoPage;
    }
}
