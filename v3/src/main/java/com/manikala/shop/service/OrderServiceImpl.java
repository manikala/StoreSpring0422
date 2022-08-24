package com.manikala.shop.service;

import com.manikala.shop.dao.OrderRepository;
import com.manikala.shop.obj.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);

    }
}
