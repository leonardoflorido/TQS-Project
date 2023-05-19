package platform.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.backend.model.Order;
import platform.backend.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findByPickupId(String pickupId) {
        return orderRepository.findByPickupId(pickupId);
    }

    public List<Order> findByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order findByEStore(String eStore) {
        return orderRepository.findByEStore(eStore);
    }
}
