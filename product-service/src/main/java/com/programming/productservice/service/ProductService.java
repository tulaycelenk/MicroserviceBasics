package com.programming.productservice.service;

import com.programming.productservice.dto.ProductRequest;
import com.programming.productservice.dto.ProductResponse;
import com.programming.productservice.model.Product;
import com.programming.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    /** TODO Buraya mapper yaz */
    public Product productRequestToProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

    }
    public ProductResponse productToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
    public void createProduct(ProductRequest productRequest){
       productRepository.save(productRequestToProduct(productRequest));
       log.info("product {} is saved", productRequest.getName());

    }
    public List<ProductResponse> getAllProducts(){
        List<Product> products= productRepository.findAll();
        return products.stream().map(this::productToProductResponse).toList();

    }


}
