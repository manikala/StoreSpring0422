package com.manikala.shop.obj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data // Геттеры сетеры и иколс
@NoArgsConstructor
@AllArgsConstructor
@Builder //DTO
@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "orders")
public class Order {

    private static final String SEQ_NAME = "order_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    //private double price;

    @CreationTimestamp
    private LocalDateTime created; //дата создания заказа
    @UpdateTimestamp
    private LocalDateTime updated; //обновления даты заказа

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private BigDecimal sum;
    //private double quantity; //количество

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    //@JoinColumn(name = "order_id") //Проверить
    private List<OrderDetails> details;

    //@ManyToOne
    //@JoinColumn (name = "product_id")
    //private Product product;
}
