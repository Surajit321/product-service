package com.productservice.ProductService.Repository;

import com.productservice.ProductService.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenSearchProductRepository extends ElasticsearchRepository<Product, Long> {
    List<Product> findDistinctByDescription(String description);
    List<Product> findAllByTitle(String title);
    List<Product> findAllByTitleContaining(String title, Pageable pageable);
    List<Product> findAllByPrice_ValueBetween(int x, int y);
    List<Product> findAllByPrice_valueGreaterThanEqual(int x);
}
