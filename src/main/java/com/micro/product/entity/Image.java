package com.micro.product.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "product_image")
public class Image {

    private String productId;
    private String imageType;
    private String name;
    private String path;
    private String size;

    @DynamoDBHashKey
    public String getProductId() {
        return productId;
    }
    @DynamoDBAttribute
    public String getImageType() {
        return imageType;
    }
    @DynamoDBAttribute
    public String getName() {
        return name;
    }
    @DynamoDBAttribute
    public String getPath() {
        return path;
    }
    @DynamoDBAttribute
    public String getSize() {
        return size;
    }
}
