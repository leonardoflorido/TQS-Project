package platform.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Admin> register(@RequestBody Admin admin) throws DetailsException {
        // Verify if the email is already registered
        if (adminService.findByEmail(admin.getEmail()) != null) {
            throw new DetailsException("Email already registered");
        }

        // Encrypt the password
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));

        // Save the admin
        return new ResponseEntity<>(adminService.save(admin), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody Admin admin) throws DetailsException {
        Admin foundAdmin = adminService.findByEmail(admin.getEmail());

        // Verify if the admin exists
        if (foundAdmin == null) {
            throw new DetailsException("Admin does not exist");
        }

        // Verify if the password is correct
        if (!new BCryptPasswordEncoder().matches(admin.getPassword(), foundAdmin.getPassword())) {
            throw new DetailsException("Incorrect password");
        }

        // Return the admin
        return new ResponseEntity<>(foundAdmin, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Admin> get(@RequestParam Long id) {
        return new ResponseEntity<>(adminService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        adminService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
