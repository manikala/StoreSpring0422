package com.manikala.shop.service;

import com.manikala.shop.obj.Order;

public interface OrderService {
    void saveOrder(Order order);
    Order getOrder(Long id);
}
