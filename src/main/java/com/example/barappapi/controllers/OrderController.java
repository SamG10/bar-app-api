package com.example.barappapi.controllers;

import com.example.barappapi.dto.CreateOrderDto;
import com.example.barappapi.dto.UpdateCocktailOrderDto;
import com.example.barappapi.dto.UpdateOrderDto;
import com.example.barappapi.enums.StepType;
import com.example.barappapi.models.Cocktail;
import com.example.barappapi.models.CocktailOrder;
import com.example.barappapi.models.Order;
import com.example.barappapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        try {
            Order order = orderService.createOrder(createOrderDto);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> updateStatusOrder(@PathVariable String id, @RequestBody UpdateOrderDto updateOrderDto) {
        try {
            Order order = orderService.updateOrderDto(id, updateOrderDto);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/cocktails")
    public ResponseEntity<List<CocktailOrder>> getCocktailsByOrderId(@PathVariable String id) {
        List<CocktailOrder> cocktails = orderService.getCocktailsByOrderId(id);
        System.out.println(cocktails);
        if (cocktails != null && !cocktails.isEmpty()) {
            return new ResponseEntity<>(cocktails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/cocktails/{cocktailId}")
    public ResponseEntity<String> updateCocktailStep(@PathVariable String id,
                                                     @PathVariable String cocktailId,
                                                     @RequestBody UpdateCocktailOrderDto updateCocktailOrderDto) {
        try {
            orderService.updateCocktailStep(id, cocktailId, updateCocktailOrderDto);
            return new ResponseEntity<>("Cocktail step updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Optional<Order> orderOptional = orderService.getOrderById(id);
        System.out.println(orderOptional);
        if (orderOptional.isPresent()) {
            return new ResponseEntity<>(orderOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
