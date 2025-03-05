package com.cloud.CloudProd.service;

import com.cloud.CloudProd.model.Products;
import com.cloud.CloudProd.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Products> getProducts() {
        return productRepository.findAll();
    }

    public Products createProduct(Products product) {
        return productRepository.save(product);
    }

    public Products getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
