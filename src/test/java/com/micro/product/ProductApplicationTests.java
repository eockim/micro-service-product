package com.micro.product;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.micro.product.entity.*;
import com.micro.product.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApplicationTests {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

//    @Autowired
//    ProductRepository repository;

    private static final String PRDUCTID = "code123";
    private static final String PRODUCTNAME = "의류";
    private static final String RETAILPRICE = "10000";
    private static final String SALEPRICE = "8000";
    private static final boolean USE = true;
    private static final String REGISTDATE = "8000";
    private static final String MODIDATE = "8000";

    final ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(5L, 5L);

    @Test
    public void deleteTables() {

        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        amazonDynamoDB.deleteTable("product");
        amazonDynamoDB.deleteTable("product_category");
        amazonDynamoDB.deleteTable("product_color");
        amazonDynamoDB.deleteTable("product_size");
        amazonDynamoDB.deleteTable("product_image");


    }

    @Test
    public void setupTable(){
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Size.class);
        tableRequest.setProvisionedThroughput(provisionedThroughput);

        CreateTableRequest tableRequest2 = dynamoDBMapper.    generateCreateTableRequest(Product.class);
        tableRequest2.setProvisionedThroughput(provisionedThroughput);

        CreateTableRequest tableRequest3 = dynamoDBMapper.generateCreateTableRequest(Image.class);
        tableRequest3.setProvisionedThroughput(provisionedThroughput);

        CreateTableRequest tableRequest4 = dynamoDBMapper.generateCreateTableRequest(Color.class);
        tableRequest4.setProvisionedThroughput(provisionedThroughput);

        CreateTableRequest tableRequest5 = dynamoDBMapper.generateCreateTableRequest(Category.class);
        tableRequest5.setProvisionedThroughput(provisionedThroughput);



        amazonDynamoDB.createTable(tableRequest);
        amazonDynamoDB.createTable(tableRequest2);
        amazonDynamoDB.createTable(tableRequest3);
        amazonDynamoDB.createTable(tableRequest4);
        amazonDynamoDB.createTable(tableRequest5);

    }

    @Test
    public void setup() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Product.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

        //dynamoDBMapper.save( new Product(PRDUCTID, PRODUCTNAME, RETAILPRICE, SALEPRICE, USE, REGISTDATE, MODIDATE));

        //dynamoDBMapper.batchDelete((List<Product>)repository.findAll());
    }

}
