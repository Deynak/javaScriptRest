package com.deynak.javaScript.rest.demo.service;

import com.deynak.javaScript.rest.demo.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(long id);
    public boolean buyProduct(long id);
    public Product getProductById(Long id);
}
