package platform.backend.pojo;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import platform.backend.record.Customer;
import platform.backend.record.Product;

import java.util.Date;
import java.util.List;

@Document("Orders")
public class OrdersPojo {
    private String pickupId;
    private Customer customer;
    private String eStore;
    private Date date;
    private List<Product> products;
    @Id
    private String id;
    private String status;

    public OrdersPojo() {
    }

    public OrdersPojo(String pickupId, Customer customer, String eStore, Date date, List<Product> products, String status) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
