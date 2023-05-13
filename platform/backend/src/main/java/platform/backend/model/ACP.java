package platform.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "acp")
public class ACP {
    private String name, email, phone, address;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String status;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Orders> orders;

    public ACP() {
    }

    public ACP(String name, String email, String phone, String password, String address, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.status = status;
        this.orders = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public void addOrder(Orders orders) {
        this.orders.add(orders);
    }

    public void removeOrder(Orders orders) {
        this.orders.remove(orders);
    }
}
