package com.cloud.CloudProd.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cloud.CloudProd.model.Products;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.cloud.CloudProd.service.ProductService;

import java.util.List;

@Component
public class ProductLambdaHandler implements RequestHandler<Object, List<Products>>{

    private static ProductService productService;

    static {
        // Khởi tạo Spring Application Context
        ApplicationContext context = new AnnotationConfigApplicationContext("com.cloud.CloudProd");
        productService = context.getBean(ProductService.class);
    }

    @Override
    public List<Products> handleRequest(Object o, Context context) {
        return productService.getProducts();
    }
}
