package platform.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import platform.backend.model.Orders;

import java.util.List;

@Repository
public interface OrdersRepository extends MongoRepository<Orders, String> {
    @Query("{ 'pickupId' : ?0 }")
    List<Orders> findByPickupId(String pickupId);

    @Query("{ 'customer.email' : ?0 }")
    List<Orders> findByCustomerEmail(String email);
}
