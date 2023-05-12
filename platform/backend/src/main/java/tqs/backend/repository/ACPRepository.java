package tqs.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.backend.model.ACP;

@Repository
public interface ACPRepository extends JpaRepository<ACP, Long> {
    ACP findByName(String name);
    ACP findByEmail(String email);
    ACP findByPhone(String phone);
    ACP findByAddress(String address);
}
