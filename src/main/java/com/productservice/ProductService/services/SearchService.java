package com.productservice.ProductService.services;

import com.productservice.ProductService.Repository.ProductRepository;
import com.productservice.ProductService.dtos.GenericDto;
import com.productservice.ProductService.models.Product;
import com.productservice.ProductService.models.SortParams;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<GenericDto> searchProducts(String query, int pageNumber, int pageSize, List<SortParams> sortParams) {
        Sort sort = null;
        if (sortParams.get(0).getSortType().equals("ASC")) {
            sort = Sort.by(sortParams.get(0).getSortParamName()).ascending();
        } else {
            sort = Sort.by(sortParams.get(0).getSortParamName()).descending();
        }

        for (int i = 1; i < sortParams.size(); i++) {
            if (sortParams.get(i).getSortType().equals("ASC")) {
                sort = Sort.by(sortParams.get(i).getSortParamName()).ascending();
            } else {
                sort = Sort.by(sortParams.get(i).getSortParamName()).descending();
            }
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        List<Product> products = productRepository.findAllByTitleContaining(query, pageRequest);
        List<GenericDto> genericDtoList = new ArrayList<>();
        for (Product product : products) {
            genericDtoList.add(product.from(product));
        }
        return genericDtoList;
    }
}
