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
import platform.backend.model.Admin;
import platform.backend.record.Login;
import platform.backend.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin) {
        // Verify if the admin already exists
        if (adminService.findByEmail(admin.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Encrypt the password
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));

        // Save the admin
        return new ResponseEntity<>(adminService.save(admin), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@Valid @RequestBody Login login) {
        // Verify if the admin exists
        Admin adminFound = adminService.findByEmail(login.email());
        if (adminFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Verify if the password is correct
        if (!new BCryptPasswordEncoder().matches(login.password(), adminFound.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Return the admin
        return new ResponseEntity<>(adminFound, HttpStatus.OK);
    }
}
