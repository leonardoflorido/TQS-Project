package platform.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import platform.backend.model.Pickup;

import java.util.List;


@Repository
public interface PickupRepository extends MongoRepository<Pickup, String> {
    @Query("{ 'email' : ?0 }")
    Pickup findByEmail(String email);

    @Query("{ 'status' : ?0 }")
    List<Pickup> findAllByStatus(String status);
}
