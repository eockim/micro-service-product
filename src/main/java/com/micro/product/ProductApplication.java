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
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableEurekaClient
@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class ProductApplication {


    @Autowired
    ProductService productService;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    private DynamoDBMapper dynamoDBMapper;

    @PreAuthorize("#oauth2.hasScope('member.admin member.user')")
    @GetMapping("/products")
    public Flux<Product> products(){

        return Flux.fromIterable(productService.products());

    }

    @PreAuthorize("#oauth2.hasScope('member.admin')")
    @PostMapping("/products")
    public Mono<Product> crateProducts(@RequestBody Product product){

        return Mono.fromCompletionStage(productService.createProduct(product));

    }

    @PreAuthorize("#oauth2.hasScope('member.admin')")
    @PostMapping("/products/createWithArray")
    public String createArray(){

        return "done";

    }

    @PreAuthorize("#oauth2.hasScope('member.admin member.user')")
    @GetMapping("/products/{productId}/{typeId}")
    public Mono<Product> getProduct(@PathVariable String productId, @PathVariable String typeId){
        return Mono.fromCompletionStage(productService.product(new ProductKey(productId, typeId)));
    }

    @PreAuthorize("#oauth2.hasScope('member.admin')")
    @PutMapping("/products/{productId}/{typeId}")
    public Mono<Product> updateProduct(@PathVariable String productId, @PathVariable String typeId, @RequestBody Product product){
        return Mono.fromCompletionStage(productService.updateProduct(new ProductKey(productId, typeId), product));
    }

    @PreAuthorize("#oauth2.hasScope('member.admin')")
    @DeleteMapping("/products/{productId}/{typeId}")
    public Flux<Product> deleteProduct(@PathVariable String productId, @PathVariable String typeId){
        return productService.deleteProduct(new ProductKey(productId, typeId));

    }

//    @GetMapping("/product/create")
//    public String productCreae(){
//
//        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
//
//        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Product.class);
//        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
//        amazonDynamoDB.createTable(tableRequest);
//
//        return "done";
//    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
