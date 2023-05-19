package platform.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.backend.model.Pickup;
import platform.backend.repository.PickupRepository;

import java.util.List;

@Service
public class PickupService {
    private final PickupRepository pickupRepository;

    @Autowired
    public PickupService(PickupRepository pickupRepository) {
        this.pickupRepository = pickupRepository;
    }

    public List<Pickup> findAll() {
        return pickupRepository.findAll();
    }

    public Pickup findById(String id) {
        return pickupRepository.findById(id).orElse(null);
    }

    public Pickup findByEmail(String email) {
        return pickupRepository.findByEmail(email);
    }

    public Pickup save(Pickup pickup) {
        return pickupRepository.save(pickup);
    }
}
