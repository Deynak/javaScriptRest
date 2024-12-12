package com.deynak.javaScript.rest.demo.service;

import com.deynak.javaScript.rest.demo.model.PurchaseHistory;
import com.deynak.javaScript.rest.demo.model.User;
import com.deynak.javaScript.rest.demo.repository.PurchaseHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    private final PurchaseHistoryRepository purchaseHistoryRepository;

    public PurchaseHistoryServiceImpl(PurchaseHistoryRepository purchaseHistoryRepository) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    public void savePurchaseHistory(PurchaseHistory purchaseHistory) {
        purchaseHistoryRepository.save(purchaseHistory);
    }

    public List<PurchaseHistory> getPurchaseHistoryForUser(User user) {
        return purchaseHistoryRepository.findByUser(user);
    }
}
