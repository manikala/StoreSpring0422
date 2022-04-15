package obj;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private int id = 0;
    private String user;
    private double totalAmount = 0;
    List<Product> products = new ArrayList<>();

    public Bucket(String user) {
        this.user = user;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

}
