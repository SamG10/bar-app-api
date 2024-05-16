package com.example.barappapi.services;

import com.example.barappapi.dto.CocktailItemDto;
import com.example.barappapi.dto.CreateOrderDto;
import com.example.barappapi.enums.StatusType;
import com.example.barappapi.models.Cocktail;
import com.example.barappapi.models.Order;
import com.example.barappapi.repositories.CocktailOrderRepository;
import com.example.barappapi.repositories.CocktailRepository;
import com.example.barappapi.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderServiceTest {
    @InjectMocks
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderService orderService;

    @Spy
    private OrderRepository orderRepository;

    @Spy
    private CocktailOrderRepository cocktailOrderRepository;

    @Spy
    private CocktailRepository cocktailRepository;

    @Test
    public void testFindAllOrders() {
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> expectedOrders = Arrays.asList(order1, order2);

        Mockito.when(orderRepository.findAll()).thenReturn(expectedOrders);

        List<Order> actualOrders = orderService.findAllOrders();

        assertEquals(expectedOrders, actualOrders);
    }
}
