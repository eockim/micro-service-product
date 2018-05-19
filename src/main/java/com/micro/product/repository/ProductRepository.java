package com.micro.product.repository;

import com.micro.product.entity.Product;
import com.micro.product.entity.key.ProductKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ProductRepository extends CrudRepository<Product, ProductKey> {

    //List<Product> findAll();

    //Product findByKey(ProductKey productKey);
}
