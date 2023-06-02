package platform.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.backend.model.Orders;
import platform.backend.pojo.OrdersPOJO;
import platform.backend.service.OrdersService;

import java.util.List;

import static platform.backend.mapper.OrdersMapper.mapPOJOToOrders;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrders(@Valid @RequestBody OrdersPOJO ordersPOJO) {
        Orders orders = mapPOJOToOrders(ordersPOJO);

        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.findAll();

        // Verify if there are any orders
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/get-by-pickup/{id}")
    public ResponseEntity<List<Orders>> getOrdersByPickupId(@PathVariable String id) {
        List<Orders> orders = ordersService.findByPickupId(id);

        // Verify if the pickup has any orders
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/update-status")
    public ResponseEntity<Orders> updateOrders(@Valid @RequestBody OrdersPOJO ordersPOJO) {
        Orders ordersFound = ordersService.findById(ordersPOJO.getId());

        // Change the status of the orders
        ordersFound.setStatus(ordersPOJO.getStatus());

        // Update the orders
        return new ResponseEntity<>(ordersService.save(ordersFound), HttpStatus.OK);
    }

    @GetMapping("/get-by-customer/{email}")
    public ResponseEntity<List<Orders>> getOrdersByCustomerEmail(@PathVariable String email) {
        List<Orders> orders = ordersService.findByCustomerEmail(email);

        // Verify if the customer has any orders
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/create-many")
    public ResponseEntity<List<Orders>> createManyOrders(@Valid @RequestBody List<Orders> orders) {
        return new ResponseEntity<>(ordersService.saveAll(orders), HttpStatus.CREATED);
    }
}
