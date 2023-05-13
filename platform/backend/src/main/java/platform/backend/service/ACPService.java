package platform.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.backend.model.ACP;
import platform.backend.repository.ACPRepository;

import java.util.List;

@Service
public class ACPService {
    private final ACPRepository acpRepository;

    @Autowired
    public ACPService(ACPRepository acpRepository) {
        this.acpRepository = acpRepository;
    }

    public ACP save(ACP acp) {
        return acpRepository.save(acp);
    }

    public ACP findById(Long id) {
        return acpRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        acpRepository.deleteById(id);
    }

    public ACP findByName(String name) {
        return acpRepository.findByName(name);
    }

    public ACP findByEmail(String email) {
        return acpRepository.findByEmail(email);
    }

    public ACP findByPhone(String phone) {
        return acpRepository.findByPhone(phone);
    }

    public ACP findByAddress(String address) {
        return acpRepository.findByAddress(address);
    }

    public List<ACP> findByStatus(String status) {
        return acpRepository.findByStatus(status);
    }

    public List<ACP> findAll() {
        return acpRepository.findAll();
    }
}
