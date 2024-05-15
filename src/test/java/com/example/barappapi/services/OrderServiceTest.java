package com.example.barappapi.services;

import com.example.barappapi.models.Order;
import com.example.barappapi.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Spy
    private OrderRepository orderRepository;

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
