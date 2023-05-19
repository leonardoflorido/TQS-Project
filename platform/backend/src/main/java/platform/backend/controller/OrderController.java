package platform.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.backend.model.Order;
import platform.backend.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get_orders")
    public ResponseEntity<List<Order>> getOrders(@RequestParam String pickupId) {
        return ResponseEntity.ok(orderService.findByPickupId(pickupId));
    }
}
