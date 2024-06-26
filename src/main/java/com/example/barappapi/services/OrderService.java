package com.example.barappapi.services;

import com.example.barappapi.common.CustomNotFoundException;
import com.example.barappapi.dto.CocktailItemDto;
import com.example.barappapi.dto.CreateOrderDto;
import com.example.barappapi.dto.UpdateCocktailOrderDto;
import com.example.barappapi.dto.UpdateOrderDto;
import com.example.barappapi.enums.StatusType;
import com.example.barappapi.enums.StepType;
import com.example.barappapi.models.*;
import com.example.barappapi.repositories.CocktailOrderRepository;
import com.example.barappapi.repositories.CocktailRepository;
import com.example.barappapi.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CocktailRepository cocktailRepository;

    @Autowired
    private CocktailOrderRepository cocktailOrderRepository;

    public Order createOrder(CreateOrderDto createOrderDto) {
        Order order = modelMapper.map(createOrderDto, Order.class);
        order = orderRepository.save(order);

        for(CocktailItemDto cocktailId : createOrderDto.getCocktails()) {
            Cocktail cocktail = cocktailRepository.findById(cocktailId.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Cocktail not found with ID: " + cocktailId));

            CocktailOrder cocktailOrder = new CocktailOrder();
            cocktailOrder.setCocktail(cocktail);
            cocktailOrder.setOrder(order);
            cocktailOrder.setPrice(cocktailId.getPrice());
            cocktailOrder.setStep(StepType.ASSEMBLY);

            cocktailOrderRepository.save(cocktailOrder);
        }

        return order;
    }

    public Order updateOrderDto(String id, UpdateOrderDto updateOrderDto) {
        Optional<Order> findOrder = orderRepository.findById(id);

        if (findOrder.isEmpty()) {
            throw new CustomNotFoundException("Order not found with id: " + id);
        }

        Order order = findOrder.get();
        order.setStatus(String.valueOf(updateOrderDto.getStatus()));
        return orderRepository.save(order);
    }

    public List<CocktailOrder> getCocktailsByOrderId(String orderId) {
        return cocktailOrderRepository.findByOrderId(orderId);
    }

    public void updateCocktailStep(String orderId, String id, UpdateCocktailOrderDto updateCocktailOrderDto) {
        Optional<CocktailOrder> cocktailOrderOptional = cocktailOrderRepository.findById(id);
        if (cocktailOrderOptional.isPresent()) {
            CocktailOrder cocktailOrder = cocktailOrderOptional.get();
            cocktailOrder.setStep(StepType.valueOf(updateCocktailOrderDto.getStep()));
            cocktailOrderRepository.save(cocktailOrder);

            /* Search cocktails by orderId */
            List<CocktailOrder> cocktailOrders = cocktailOrderRepository.findByOrderId(orderId);

            boolean allCompleted = cocktailOrders.stream().allMatch(co -> co.getStep() == StepType.COMPLETED);
            boolean anyMatchDressage = cocktailOrders.stream().anyMatch(co -> co.getStep() == StepType.DRESSAGE);

            Optional<Order> orderOptional = orderRepository.findById(orderId);

            if(anyMatchDressage && orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setStatus(String.valueOf(StatusType.IN_PROGRESS));
                orderRepository.save(order);
            }

            if (allCompleted && orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setStatus(String.valueOf(StatusType.COMPLETED));
                orderRepository.save(order);
            }
        } else {
            throw new RuntimeException("Cocktail not found in order");
        }
    }

    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
}
