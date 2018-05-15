package com.micro.product.repository;

import com.micro.product.entity.Size;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface ProductSizeRepository extends CrudRepository<Size, String> {

}
