package com.manikala.shop.dto;

import com.manikala.shop.obj.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderIntegrationDTO { //дтошка для общения между сервисами (добавить новое или что то убрать)
    private Long orderId;
    private String username;
    private List<OrderDetailsDTO> details;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailsDTO {
        private String product;
        private Double price;
        private Double amount;
        private Double sum;

        public OrderDetailsDTO(OrderDetails details) {
            this.product = details.getProduct().getTitle();
            this.price = details.getPrice().doubleValue();
            this.amount = details.getAmount().doubleValue();
            this.sum = details.getPrice().multiply(details.getAmount()).doubleValue();
        }
    }
}