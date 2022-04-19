package obj;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "buckets")
public class Bucket {
    @Id
    private int id = 0;
    //private String user;
    private double totalAmount = 0;
    //List<Product> products = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable (name = "buckets_products",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn (name = "product_id"))
    private List<Product> product;

//    public Bucket(String user) {
//        this.user = user;
//    }

//    public void addProduct(Product product) {
//        products.add(product);
    }


