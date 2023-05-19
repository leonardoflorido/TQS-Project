package platform.backend.model;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Product")
public class Product {
    private final String name;
    private final Float price;
    private final int quantity;
    @Id
    private String id;

    public Product(String name, Float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
