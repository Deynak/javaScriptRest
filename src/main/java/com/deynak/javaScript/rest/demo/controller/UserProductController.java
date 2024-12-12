package com.deynak.javaScript.rest.demo.controller;

import com.deynak.javaScript.rest.demo.model.Product;
import com.deynak.javaScript.rest.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/products")
public class UserProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/buy/{id}")
    public ResponseEntity<String> buyProduct(@PathVariable int id) {
        boolean success = productService.buyProduct(id);
        if (success) {
            return ResponseEntity.ok("Product purchased successfully");
        } else {
            return ResponseEntity.badRequest().body("Product out of stock");
        }
    }
}