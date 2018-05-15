package com.micro.product.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.micro.product.Customer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@DynamoDBTable(tableName = "Product")
@Setter
@RequiredArgsConstructor
public class Product {

    private String id;
    @NonNull
    private String productId;
    @NonNull
    private String name;

    @NonNull
    private String retailPrice;
    @NonNull
    private String salePrice;

    @NonNull
    private boolean use;
    @NonNull
    private String registDate;
    @NonNull
    private String modiDate;
    private Set<Color> colors;
    private Set<Category> categories;
    private Set<Size> sizes;
    private Set<Image> images;

    public Product(){}

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId(){
        return id;
    }
    @DynamoDBAttribute
    public String getName() {
        return name;
    }
    @DynamoDBAttribute
    public String getProductId() {
        return productId;
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
    public Set<Category> getCategories() {
        return categories;
    }
    @DynamoDBAttribute
    public Set<Color> getColors() {
        return colors;
    }
    @DynamoDBAttribute
    public Set<Size> getSizes() {
        return sizes;
    }
    @DynamoDBAttribute
    public Set<Image> getImages() {
        return images;
    }
}
