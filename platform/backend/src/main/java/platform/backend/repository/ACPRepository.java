package platform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.backend.model.ACP;

import java.util.List;

@Repository
public interface ACPRepository extends JpaRepository<ACP, Long> {
    ACP findByName(String name);

    ACP findByEmail(String email);

    ACP findByPhone(String phone);

    ACP findByAddress(String address);

    List<ACP> findByStatus(String status);
}
