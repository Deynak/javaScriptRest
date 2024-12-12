package com.deynak.javaScript.rest.demo.repository;

import com.deynak.javaScript.rest.demo.model.PurchaseHistory;
import com.deynak.javaScript.rest.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    List<PurchaseHistory> findByUser(User user);
}
