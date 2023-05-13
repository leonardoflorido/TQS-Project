package platform.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    private String name, price;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Product() {
    }

    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
