package platform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.backend.model.Customer;
import platform.backend.model.Order;

import java.util.Date;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByCustomer(Customer customer);

    Order findByeStore(String eStore);

    Order findByDate(Date date);
}
