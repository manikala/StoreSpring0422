package obj;

import jakarta.persistence.*;

@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "orders")
public class Order {
    private static final String SEQ_NAME = "order_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private int id = 0;
    //private double price;
    private double totalAmount = 0;
    private int quantity; //количество
    @OneToMany
    @JoinColumn (name = "product_id")
    private Product product;


}
