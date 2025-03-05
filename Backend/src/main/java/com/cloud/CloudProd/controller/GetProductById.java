package com.cloud.CloudProd.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cloud.CloudProd.model.Products;
import com.cloud.CloudProd.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GetProductById implements RequestHandler<String, Products> {

    private static ProductService productService;

    static {
        // Khởi tạo Spring Application Context
        ApplicationContext context = new AnnotationConfigApplicationContext("com.cloud.CloudProd");
        productService = context.getBean(ProductService.class);
    }

    public Products handleRequest(String productId, Context context) {
        return productService.getProductById(productId);
    }

}