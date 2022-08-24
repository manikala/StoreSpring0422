package com.manikala.shop.service;

import com.manikala.shop.config.OrderIntegrationConfig;
import com.manikala.shop.dao.OrderRepository;
import com.manikala.shop.dto.OrderIntegrationDTO;
import com.manikala.shop.obj.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderIntegrationConfig integrationConfig;

    public OrderServiceImpl(OrderRepository orderRepository, OrderIntegrationConfig integrationConfig) {
        this.orderRepository = orderRepository;
        this.integrationConfig = integrationConfig;
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        sendIntegrationNotify(savedOrder);

    }

    private void sendIntegrationNotify(Order order) {
        OrderIntegrationDTO dto = new OrderIntegrationDTO(); //запрашиваем из дто нужные данные
        dto.setUsername(order.getUser().getName());
        dto.setOrderId(order.getId());
        List<OrderIntegrationDTO.OrderDetailsDTO> details = order.getDetails().stream()
                .map(OrderIntegrationDTO.OrderDetailsDTO::new).collect(Collectors.toList());
        dto.setDetails(details);

        Message<OrderIntegrationDTO> message = MessageBuilder.withPayload(dto)
                .setHeader("Content-type", "application/json") //и указываем в каком типе мы будет все это брать
                .build();

        integrationConfig.getOrdersChannel().send(message);
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
