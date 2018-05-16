package com.micro.product.repository;

import com.micro.product.entity.Image;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductImageRepository extends CrudRepository<Image, String> {
}
