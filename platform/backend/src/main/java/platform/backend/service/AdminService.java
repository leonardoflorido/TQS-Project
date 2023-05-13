package platform.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.backend.model.Admin;
import platform.backend.repository.AdminRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }

    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
