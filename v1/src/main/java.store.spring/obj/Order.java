package obj;

import jakarta.persistence.*;

@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "orders")
public class Order {

    @Id
    private int id = 0;
    private double price;
    private double totalAmount = 0;
    @OneToMany
    @JoinColumn (name = "product_id")
    private Product product;


}
