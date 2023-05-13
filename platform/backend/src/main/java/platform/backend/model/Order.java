package platform.backend.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "Order")
public class Order {
    private String eStore;
    private Date date;
    @ElementCollection
    @CollectionTable(name = "Order_Products", joinColumns = @JoinColumn(name = "Order_id"))
    @MapKeyJoinColumn(name = "Product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> products;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @Id
    @GeneratedValue
    private Long id;
    private String status;

    public Order() {
    }

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
