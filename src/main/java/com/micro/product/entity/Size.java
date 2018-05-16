package com.micro.product.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "product_size")
@Setter
public class Size {

    private String productId;
    //private String sizeId;
    private String sizeName;

    public Size(){}

//    @DynamoDBRangeKey
//    public String getSizeId() {
//        return sizeId;
//    }


    @DynamoDBHashKey
    public String getProductId() {
        return productId;
    }

    @DynamoDBAttribute
    public String getSizeName() {
        return sizeName;
    }
}
