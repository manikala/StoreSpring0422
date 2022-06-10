package com.store.shoptwo.obj;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data // Геттеры сетеры и иколс
@NoArgsConstructor
@AllArgsConstructor
@Builder //DTO
@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "products")
public class Product {
    private static final String SEQ_NAME = "product_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private int id = 0;
    private int vendor; //артикул
    private String name;
    private double price;
    private int amount;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (name = "products_categories",
            joinColumns = @JoinColumn (name = "product_id"),
            inverseJoinColumns = @JoinColumn (name = "category_id"))
    private List<Category> categories;


}
