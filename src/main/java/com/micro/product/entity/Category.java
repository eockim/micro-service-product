package com.micro.product.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "Category")
public class Category {
    @Id
    private String productId;
    private String categoryId;
    private String categoryName;

    @DynamoDBHashKey
    public String getProductId() {
        return productId;
    }
    @DynamoDBAttribute
    public String getCategoryId() {
        return categoryId;
    }
    @DynamoDBAttribute
    public String getCategoryName() {
        return categoryName;
    }
}
