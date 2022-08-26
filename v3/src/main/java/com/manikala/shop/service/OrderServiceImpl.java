package com.manikala.shop.service;

import com.manikala.shop.dao.OrderRepository;
import com.manikala.shop.obj.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);

    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
