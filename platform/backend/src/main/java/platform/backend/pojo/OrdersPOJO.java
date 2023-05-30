package platform.backend.pojo;

import platform.backend.record.Customer;
import platform.backend.record.Product;

import java.util.Date;
import java.util.List;

public class OrdersPOJO {
    private String id;
    private String pickupId;
    private Customer customer;
    private String eStore;
    private Date date;
    private List<Product> products;
    private String status;

    public OrdersPOJO() {
    }

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

    public void setPickupId(String pickupId) {
        this.pickupId = pickupId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String geteStore() {
        return eStore;
    }

    public void seteStore(String eStore) {
        this.eStore = eStore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

