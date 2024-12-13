package com.deynak.javaScript.rest.demo.controller;

import com.deynak.javaScript.rest.demo.model.Product;
import com.deynak.javaScript.rest.demo.model.PurchaseHistory;
import com.deynak.javaScript.rest.demo.model.User;
import com.deynak.javaScript.rest.demo.service.ProductService;
import com.deynak.javaScript.rest.demo.service.PurchaseHistoryService;
import com.deynak.javaScript.rest.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class PurchaseController {

    private final UserService userService;
    private final ProductService productService;
    private final PurchaseHistoryService purchaseHistoryService;

    public PurchaseController(UserService userService, ProductService productService, PurchaseHistoryService purchaseHistoryService) {
        this.userService = userService;
        this.productService = productService;
        this.purchaseHistoryService = purchaseHistoryService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseProduct(@RequestParam Long productId, @RequestParam int quantity, Principal principal) {

        User user = (User) userService.loadUserByUsername(principal.getName());
        Double balance = user.getBalance() != null ? user.getBalance() : 0.0;
        Product product = productService.getProductById(productId);

        if (product.getQuantity() < quantity) {
            return ResponseEntity.badRequest().body("Not enough stock available");
        }

        double totalCost = product.getPrice() * quantity;

        if (user.getBalance() < totalCost) {
            return ResponseEntity.badRequest().body("Insufficient funds");
        }

        user.setBalance(user.getBalance() - totalCost);
        userService.saveUser(user);

        product.setQuantity(product.getQuantity() - quantity);
        productService.updateProduct(product);

        PurchaseHistory purchaseHistory = new PurchaseHistory(user, product, quantity, LocalDateTime.now());
        purchaseHistoryService.savePurchaseHistory(purchaseHistory);

        return ResponseEntity.ok("Purchase successful");
    }

    @GetMapping("/balance")
    public ResponseEntity<Map<String, Double>> getUserBalance(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        Map<String, Double> response = new HashMap<>();
        response.put("balance", user.getBalance());
        return ResponseEntity.ok(response);
    }
}

