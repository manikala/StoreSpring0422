package obj;

public class Product {
    private int id = 0;
    private int vendor;
    private String name;
    private double price;
    private int amount;
    private String description;

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
