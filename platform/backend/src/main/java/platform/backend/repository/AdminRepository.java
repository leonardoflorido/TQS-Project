package platform.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import platform.backend.model.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    @Query("{ 'email' : ?0 }")
    Admin findByEmail(String email);
}
