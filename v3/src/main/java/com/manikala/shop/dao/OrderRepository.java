package com.manikala.shop.dao;

import com.manikala.shop.obj.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
