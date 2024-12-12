package com.deynak.javaScript.rest.demo.service;

import com.deynak.javaScript.rest.demo.model.PurchaseHistory;
import com.deynak.javaScript.rest.demo.model.User;

import java.util.List;

public interface PurchaseHistoryService {

    public void savePurchaseHistory(PurchaseHistory purchaseHistory);

    public List<PurchaseHistory> getPurchaseHistoryForUser(User user);
}
