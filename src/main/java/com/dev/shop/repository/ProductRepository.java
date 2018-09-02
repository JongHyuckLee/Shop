package com.dev.shop.repository;

import com.dev.shop.mongo.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products, String>{
}
