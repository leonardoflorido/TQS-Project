package platform.backend.model;


import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import platform.backend.record.Customer;
import platform.backend.record.Product;

import java.util.Date;
import java.util.List;

@Document("Orders")
public class Orders {
    private final String pickupId;
    private final Customer customer;
    private final String eStore;
    private final Date date;
    private final List<Product> products;
    @Id
    private String id;
    private String status;

    public Orders(String pickupId, Customer customer, String eStore, Date date, List<Product> products, String status) {
        this.pickupId = pickupId;
        this.customer = customer;
        this.eStore = eStore;
        this.date = date;
        this.products = products;
        this.status = status;
    }

    public String getPickupId() {
        return pickupId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String geteStore() {
        return eStore;
    }

    public Date getDate() {
        return date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
