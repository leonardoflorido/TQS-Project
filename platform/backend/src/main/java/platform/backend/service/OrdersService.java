package platform.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.backend.model.Orders;
import platform.backend.repository.OrdersRepository;

import java.util.List;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public Orders findById(String id) {
        return ordersRepository.findById(id).orElse(null);
    }

    public List<Orders> findByPickupId(String pickupId) {
        return ordersRepository.findByPickupId(pickupId);
    }

    public List<Orders> findByCustomerEmail(String email) {
        return ordersRepository.findByCustomerEmail(email);
    }

    public List<Orders> saveAll(List<Orders> orders) {
        return ordersRepository.saveAll(orders);
    }
}
