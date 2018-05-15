package com.micro.product;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface CustomerRepository extends CrudRepository<Customer, String> {

    List<Customer> findByLastName(String lastName);
}
