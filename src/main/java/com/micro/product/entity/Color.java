package com.micro.product.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "product_color")
public class Color {

    private String productId;
    private String colorId;
    private String colorName;

    @DynamoDBHashKey
    public String getProductId() {
        return productId;
    }
    @DynamoDBAttribute
    public String getColorId() {
        return colorId;
    }
    @DynamoDBAttribute
    public String getColorName() {
        return colorName;
    }
}
