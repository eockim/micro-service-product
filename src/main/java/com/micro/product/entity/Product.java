package com.micro.product.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@DynamoDBTable(tableName = "product")
public class Product {

    private String productId;
    private String typeCode; // 자켓 , 바지 , 셔츠..
    private String name;
    private String description; // 설명
    private String retailPrice;
    private String salePrice;
    private boolean use;
    private String registDate;
    private String modiDate;

    public Product(){}

    @DynamoDBHashKey
    public String getProductId() {
        return productId;
    }
    @DynamoDBAttribute
    public boolean isUse() {
        return use;
    }
    @DynamoDBAttribute
    public String getName() {
        return name;
    }
    @DynamoDBAttribute
    public String getRetailPrice() {
        return retailPrice;
    }
    @DynamoDBAttribute
    public String getSalePrice() {
        return salePrice;
    }
    @DynamoDBAttribute
    public String getModiDate() {
        return modiDate;
    }
    @DynamoDBAttribute
    public String getRegistDate() {
        return registDate;
    }
    @DynamoDBAttribute
    public String getDescription() {
        return description;
    }
    @DynamoDBRangeKey
    public String getTypeCode() {
        return typeCode;
    }
}
