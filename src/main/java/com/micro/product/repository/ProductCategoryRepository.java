package com.micro.product.repository;

import com.micro.product.entity.Category;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductCategoryRepository extends CrudRepository<Category, String> {
}
