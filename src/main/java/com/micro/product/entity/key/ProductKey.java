package com.micro.product.entity.key;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductKey implements Serializable {

    private String productId;

    private String typeId;



    @DynamoDBHashKey
    public String getProductId() {
        return productId;
    }
    @DynamoDBRangeKey
    public String getTypeId() {
        return typeId;
    }
}
