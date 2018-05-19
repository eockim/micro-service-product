package com.micro.product;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.micro.product.entity.Product;
import com.micro.product.entity.key.ProductKey;
import com.micro.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class ProductApplication {


    @Autowired
    ProductService productService;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    private DynamoDBMapper dynamoDBMapper;

    @GetMapping("/products")
    public Flux<Product> products(){

        return Flux.fromIterable(productService.products());

    }

    @PostMapping("/products")
    public Mono<Product> crateProducts(@RequestBody Product product){

        return Mono.fromCompletionStage(productService.createProduct(product));

    }

    @PostMapping("/products/createWithArray")
    public String createArray(){

        return "done";

    }

    @GetMapping("/products/{productId}/{typeId}")
    public Mono<Product> getProduct(@PathVariable String productId, @PathVariable String typeId){
        return Mono.fromCompletionStage(productService.product(new ProductKey(productId, typeId)));
    }

    @PutMapping("/products{productId}/{typeId}")
    public Mono<Product> updateProduct(@PathVariable String productId, @PathVariable String typeId, @RequestBody Product product){
        return Mono.fromCompletionStage(productService.updateProduct(new ProductKey(productId, typeId), product));
    }

    @DeleteMapping("/products/{productId}/{typeId}")
    public Flux<Product> deleteProduct(@PathVariable String productId, @PathVariable String typeId){
        return productService.deleteProduct(new ProductKey(productId, typeId));

    }


    @GetMapping("/product/create")
    public String productCreae(){

        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Product.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

        return "done";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
