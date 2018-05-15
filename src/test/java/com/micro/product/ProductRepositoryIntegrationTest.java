package com.micro.product;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.micro.product.Entity.Product;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("local")
public class ProductRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    ProductRepository repository;

    private static final String PRDUCTID = "code123";
    private static final String PRODUCTNAME = "의류";
    private static final String RETAILPRICE = "10000";
    private static final String SALEPRICE = "8000";
    private static final boolean USE = true;
    private static final String REGISTDATE = "8000";
    private static final String MODIDATE = "8000";

    @Test
    public void setup() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Product.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

        // your code here...

        dynamoDBMapper.batchDelete((List<Product>)repository.findAll());
    }

    @Test
    public void dynamoDBTestCase() {

        Product product = new Product(PRDUCTID, PRODUCTNAME, RETAILPRICE, SALEPRICE, USE, REGISTDATE, MODIDATE);
        repository.save(product);

        List<Product> list = (List<Product>) repository.findAll();

        assertTrue("Book found.", list.size() > 0);
        assertTrue("The book name is correct.", list.get(0).getProductId().equals(PRDUCTID));
    }
}
