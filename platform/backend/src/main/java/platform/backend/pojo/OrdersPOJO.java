package platform.backend.pojo;

import platform.backend.record.Customer;
import platform.backend.record.Product;

import java.util.Date;
import java.util.List;

public class OrdersPOJO {
    private final String pickupId;
    private final Customer customer;
    private final String eStore;
    private final Date date;
    private final List<Product> products;
    private String id;
    private String status;

    public OrdersPOJO(String id, String pickupId, Customer customer, String eStore, Date date, List<Product> products, String status) {
        this.id = id;
        this.pickupId = pickupId;
        this.customer = customer;
        this.eStore = eStore;
        this.date = date;
        this.products = products;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

