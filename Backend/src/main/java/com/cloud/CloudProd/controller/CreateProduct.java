package com.cloud.CloudProd.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cloud.CloudProd.model.Products;
import com.cloud.CloudProd.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CreateProduct implements RequestHandler<Object, Products> {
    private static ProductService productService;

    static {
        // Khởi tạo Spring Application Context
        ApplicationContext context = new AnnotationConfigApplicationContext("com.cloud.CloudProd");
        productService = context.getBean(ProductService.class);
    }

    @Override
    public Products handleRequest(Object input, Context context) {
        return productService.createProduct((Products) input);
    }
}
