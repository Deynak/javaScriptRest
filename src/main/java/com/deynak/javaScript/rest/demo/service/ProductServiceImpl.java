package com.deynak.javaScript.rest.demo.service;

import com.deynak.javaScript.rest.demo.dao.ProductRepository;
import com.deynak.javaScript.rest.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public boolean buyProduct(long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null && product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
            return true;
        }
        return false;
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

}
