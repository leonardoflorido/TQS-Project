package platform.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.backend.exception.DetailsException;
import platform.backend.model.Admin;
import platform.backend.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin) throws DetailsException {
        // Verify if the admin already exists
        if (adminService.findByEmail(admin.getEmail()) != null) {
            throw new DetailsException("Email already registered");
        }

        // Encrypt the password
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));

        // Save the admin
        return new ResponseEntity<>(adminService.save(admin), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@Valid @RequestBody Admin admin) throws DetailsException {
        // Verify if the admin exists
        Admin adminFound = adminService.findByEmail(admin.getEmail());
        if (adminFound == null) {
            throw new DetailsException("Email does not exist");
        }

        // Verify if the password is correct
        if (!new BCryptPasswordEncoder().matches(admin.getPassword(), adminFound.getPassword())) {
            throw new DetailsException("Incorrect password");
        }

        // Return the admin
        return new ResponseEntity<>(adminFound, HttpStatus.OK);
    }
}
