package platform.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import platform.backend.model.Pickup;
import platform.backend.record.Login;
import platform.backend.service.PickupService;

import java.util.List;

@RestController
@RequestMapping("/pickup")
public class PickupController {
    private final PickupService pickupService;

    @Autowired
    public PickupController(PickupService pickupService) {
        this.pickupService = pickupService;
    }

    @PostMapping("/register")
    public ResponseEntity<Pickup> registerPickup(@Valid @RequestBody Pickup pickup) {
        // Verify if the pickup already exists
        if (pickupService.findByEmail(pickup.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Encrypt the password
        pickup.setPassword(new BCryptPasswordEncoder().encode(pickup.getPassword()));

        // Save the pickup
        return new ResponseEntity<>(pickupService.save(pickup), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Pickup> loginPickup(@Valid @RequestBody Login login) {
        // Verify if the pickup exists
        Pickup pickupFound = pickupService.findByEmail(login.email());
        if (pickupFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println(pickupFound.getStatus());

        // Verify if the pickup is a partner
        if (!pickupFound.getStatus().equals("Partner")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Verify if the password is correct
        if (!new BCryptPasswordEncoder().matches(login.password(), pickupFound.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Return the pickup
        return new ResponseEntity<>(pickupFound, HttpStatus.OK);
    }

    @PutMapping("/update-status")
    public ResponseEntity<Pickup> updatePickupStatus(@Valid @RequestBody Pickup pickup) {
        Pickup pickupFound = pickupService.findByEmail(pickup.getEmail());

        // Change the pickup's status
        pickupFound.setStatus(pickup.getStatus());

        // Update the pickup
        return new ResponseEntity<>(pickupService.save(pickupFound), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Pickup>> getAllPickups() {
        List<Pickup> pickups = pickupService.findAll();

        // Verify if there are any pickups
        if (pickups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pickups, HttpStatus.OK);
    }

    @PostMapping("/register-many")
    public ResponseEntity<List<Pickup>> registerManyPickups(@Valid @RequestBody List<Pickup> pickups) {
        // Verify if the pickups already exist
        for (Pickup pickup : pickups) {
            if (pickupService.findByEmail(pickup.getEmail()) != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        // Encrypt the passwords
        for (Pickup pickup : pickups) {
            pickup.setPassword(new BCryptPasswordEncoder().encode(pickup.getPassword()));
        }

        // Save the pickups
        return new ResponseEntity<>(pickupService.saveAll(pickups), HttpStatus.CREATED);
    }
}
