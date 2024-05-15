package com.example.barappapi.repositories;

import com.example.barappapi.models.CocktailOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CocktailOrderRepository extends JpaRepository<CocktailOrder, String> {
    List<CocktailOrder> findByOrderId(String orderId);
}
