package main.java.platform.backend.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import platform.backend.record.Customer;
import platform.backend.record.Product;

import java.util.Date;
import java.util.List;

public class OrdersDTO {
    @NotEmpty
    private String pickupId;

    @NotNull
    private Customer customer;

    @NotEmpty
    private String eStore;

    @NotNull
    private Date date;

    @NotEmpty
    private List<Product> products;

    @NotEmpty
    private String status;

    // Constructors, getters, and setters

    public OrdersDTO() {
    }

    public OrdersDTO(String pickupId, Customer customer, String eStore, Date date, List<Product> products, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
