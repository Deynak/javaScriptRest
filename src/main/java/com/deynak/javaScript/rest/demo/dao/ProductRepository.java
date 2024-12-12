package com.deynak.javaScript.rest.demo.dao;

import com.deynak.javaScript.rest.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
