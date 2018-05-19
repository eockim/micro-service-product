package com.micro.product.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.micro.product.entity.key.ProductKey;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@DynamoDBTable(tableName = "product")
public class Product {

    @Id
    private ProductKey key;
    //private String productId;
    //private String typeCode; // 자켓 , 바지 , 셔츠..
    private String name;
    private String description; // 설명
    private String retailPrice;
    private String salePrice;
    private boolean use;
    private String registDate;
    private String modiDate;

    public Product(){}

    private Product(ProductKey key, String name, String registDate) {
        this.key = key;
        this.name = name;
        this.registDate = registDate;
    }

    public Product(String productId, String typeId, String name, String lastChange) {
        this(new ProductKey(productId, typeId), name, lastChange);

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

    @DynamoDBHashKey(attributeName = "productId")
    public String getProductId() {
        return (key != null) ? key.getProductId() : null;
    }

    public void setProductId(String productId) {
        if (key == null) {
            key = new ProductKey();
        }
        key.setProductId(productId);
    }

    @DynamoDBRangeKey(attributeName = "typeId")
    public String getTypeId() {
        return (key != null) ? key.getTypeId() : null;
    }

    public void setTypeId(String typeId) {
        if (key == null) {
            key = new ProductKey();
        }
        key.setTypeId(typeId);
    }
}
