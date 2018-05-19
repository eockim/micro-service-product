package com.micro.product.service;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.micro.product.entity.Product;
import com.micro.product.entity.key.ProductKey;
import com.micro.product.repository.ProductRepository;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Async
    public CompletableFuture<Product> createProduct(Product product){

        return CompletableFuture.completedFuture(productRepository.save(product));
    }

    public Iterable<Product> products(){
        Iterable<Product> test = null;
        List<Product> test2 = new ArrayList<Product>();
        Product p1 = new Product();
        test2.add(p1);
        return Optional.<Iterable<Product>>ofNullable(productRepository.findAll()).orElse(new ArrayList<>()) ;
    }

    @Async
    public CompletableFuture<Product> product(ProductKey productKey){
        return CompletableFuture.completedFuture(productRepository.findById(productKey).orElse(new Product()));
    }

    @Async
    public Flux<Product> deleteProduct(ProductKey productKey){

        productRepository.deleteById(productKey);

        return Flux.fromIterable(productRepository.findAll());

    }

    @Async
    public CompletableFuture<Product> updateProduct(ProductKey productKey, Product product){

        Optional<Product> findProduct = productRepository.findById(productKey);
        findProduct
                .ifPresent(x -> {
                    productRepository.save(product);
                });

        return CompletableFuture.completedFuture(findProduct.orElse(new Product()));
    }


}
