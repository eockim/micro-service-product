package com.micro.product;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.micro.product.entity.Product;
import com.micro.product.entity.Size;
import com.micro.product.repository.ProductSizeRepository;
import com.micro.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ProductApplication {

//    @Autowired
//    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    private DynamoDBMapper dynamoDBMapper;

    @GetMapping("/product/create")
    public String productCreae(){

        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Product.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

        return "done";
    }

    @GetMapping("/product/size")
    public String saveProduct(){


        String result = "";
        Iterable<Size> customers = productSizeRepository.findAll();

        for (Size cust : customers) {
            result += cust.toString() + "<br>";
        }

        return result;
    }

//    @GetMapping("/delete")
//    public String delete() {
//        repository.deleteAll();
//        return "Done";
//    }
//
//    @GetMapping("/save")
//    public String save() {
//        // save a single Customer
//        repository.save(new Customer("JSA-1", "Jack", "Smith"));
//
//        // save a list of Customers
////        repository.save(Arrays.asList(new Customer("JSA-2", "Adam", "Johnson"), new Customer("JSA-3", "Kim", "Smith"),
////                new Customer("JSA-4", "David", "Williams"), new Customer("JSA-5", "Peter", "Davis")));
//
//        return "Done";
//    }
//
//    @GetMapping("/findall")
//    public String findAll() {
//        String result = "";
//        Iterable<Customer> customers = repository.findAll();
//
//        for (Customer cust : customers) {
//            result += cust.toString() + "<br>";
//        }
//
//        return result;
//    }
//
//    @GetMapping("/findbyid/{id}")
//    public String findById(@PathVariable String id) {
//
//        String result = "";
//        result = repository.findById(id).toString();
//
//        return result;
//    }
//
//    @GetMapping("/findbylastname/{lastName}")
//    public String fetchDataByLastName(@PathVariable String lastName) {
//        String result = "";
//
//        for (Customer cust : repository.findByLastName(lastName)) {
//            result += cust.toString() + "<br>";
//        }
//
//        return result;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
