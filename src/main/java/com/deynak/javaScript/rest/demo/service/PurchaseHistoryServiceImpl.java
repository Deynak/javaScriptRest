package com.deynak.javaScript.rest.demo.service;

import com.deynak.javaScript.rest.demo.model.PurchaseHistory;
import com.deynak.javaScript.rest.demo.model.User;
import com.deynak.javaScript.rest.demo.repository.PurchaseHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final UserService userService;

    public PurchaseHistoryServiceImpl(PurchaseHistoryRepository purchaseHistoryRepository, UserService userService) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
        this.userService = userService;
    }

    public void savePurchaseHistory(PurchaseHistory purchaseHistory) {
        purchaseHistoryRepository.save(purchaseHistory);
    }

    public List<PurchaseHistory> getPurchaseHistoryForUser(User user) {
        return purchaseHistoryRepository.findByUser(user);
    }

    @Override
    public void processPurchase(User user, double productPrice, int quantity) {
        double totalCost = productPrice * quantity;

        if (user.getBalance() < totalCost) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // Списываем средства
        user.setBalance(user.getBalance() - totalCost);
        userService.saveUser(user);
    }
}
