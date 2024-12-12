package com.deynak.javaScript.rest.demo.controller;

import com.deynak.javaScript.rest.demo.model.PurchaseHistory;
import com.deynak.javaScript.rest.demo.model.User;
import com.deynak.javaScript.rest.demo.service.PurchaseHistoryService;
import com.deynak.javaScript.rest.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/user/purchase-history")
public class PurchaseHistoryController {

    private final PurchaseHistoryService purchaseHistoryService;
    private final UserService userService;

    public PurchaseHistoryController(PurchaseHistoryService purchaseHistoryService, UserService userService) {
        this.purchaseHistoryService = purchaseHistoryService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> addPurchase(@RequestBody PurchaseHistory purchaseHistory, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        purchaseHistory.setUser(user);
        purchaseHistory.setPurchaseDate(LocalDateTime.now());
        purchaseHistoryService.savePurchaseHistory(purchaseHistory);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<PurchaseHistory>> getPurchaseHistory(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(purchaseHistoryService.getPurchaseHistoryForUser(user));
    }
}
