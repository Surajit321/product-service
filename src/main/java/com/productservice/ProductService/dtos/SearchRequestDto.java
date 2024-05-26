package com.productservice.ProductService.dtos;

import com.productservice.ProductService.models.SortParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    private String query;
    private int pageNumber;
    private int itemsPerPage;
    private List<SortParams> sortParams;
}
