package platform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.backend.model.Customer;
import platform.backend.model.Orders;

import java.util.Date;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByCustomer(Customer customer);

    Orders findByeStore(String eStore);

    Orders findByDate(Date date);
}
