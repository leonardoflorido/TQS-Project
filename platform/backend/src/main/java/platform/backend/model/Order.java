package platform.backend.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Map;

@Table(name = "Order")
public class Order {
    private final String eStore;
    private final Date date;
    private final Map<Product, Integer> products;
    private final Customer customer;
    @Id
    @GeneratedValue
    private Long id;
    private String status;

    public Order(String eStore, Date date, Map<Product, Integer> products, String status, Customer customer) {
        this.eStore = eStore;
        this.date = date;
        this.products = products;
        this.status = status;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String geteStore() {
        return eStore;
    }

    public Date getDate() {
        return date;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }
}
