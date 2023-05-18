package platform.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import platform.backend.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{ 'pickupId' : ?0 }")
    Order findByPickupId(String pickupId);

    @Query("{ 'customerId' : ?0 }")
    Order findByCustomerId(String customerId);

    @Query("{ 'eStore' : ?0 }")
    Order findByEStore(String eStore);
}
