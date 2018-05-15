package com.micro.product.repository;

import com.micro.product.entity.Size;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SizeRepository extends CrudRepository<Size, String> {

    @Override
    Optional<Size> findById(String s);
}
