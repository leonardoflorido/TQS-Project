package platform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.backend.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);

    Customer findByEmail(String email);

    Customer findByPhone(String phone);

    Customer findByAddress(String address);
}
