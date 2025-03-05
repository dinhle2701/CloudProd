package com.cloud.CloudProd.repository;

import com.cloud.CloudProd.model.Products;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final DynamoDbTable<Products> productTable;

    public ProductRepository(DynamoDbClient dbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dbClient)
                .build();
        this.productTable = enhancedClient.table("Products", TableSchema.fromBean(Products.class));
    }

    public void save(Products product) {
        productTable.putItem(product);
    }

    public Optional<Products> findById(String id) {
        return Optional.ofNullable(productTable.getItem(r -> r.key(k -> k.partitionValue(id))));
    }

    public List<Products> findAll() {
        return productTable.scan().items().stream().toList();
    }

    public void deleteById(String id) {
        productTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
