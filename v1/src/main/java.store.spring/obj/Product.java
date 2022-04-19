package obj;

import jakarta.persistence.*;

import java.util.List;

@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "products")
public class Product {
    @Id
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


    public Product(int vendor, String name, double price) {
        this.vendor = vendor;
        this.name = name;
        this.price = price;
        id = id + 1;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }


}
