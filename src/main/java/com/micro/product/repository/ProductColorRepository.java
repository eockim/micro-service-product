package com.micro.product.repository;

import com.micro.product.entity.Color;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductColorRepository extends CrudRepository<Color, String> {
}
