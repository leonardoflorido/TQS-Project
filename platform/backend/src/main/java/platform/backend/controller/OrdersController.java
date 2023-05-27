package platform.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.backend.model.Orders;
import platform.backend.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrders(@Valid @RequestBody Orders orders) {
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.findAll();

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<Orders>> getOrdersByPickupId(@PathVariable String id) {
        List<Orders> orders = ordersService.findByPickupId(id);

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
