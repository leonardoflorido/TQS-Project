package platform.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import platform.backend.model.Pickup;
import platform.backend.service.PickupService;

import java.util.Objects;

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
    public ResponseEntity<Pickup> loginPickup(@Valid @RequestBody Pickup pickup) {
        // Verify if the pickup exists
        Pickup pickupFound = pickupService.findByEmail(pickup.getEmail());
        if (pickupFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Verify if the pickup is a partner
        if (!Objects.equals(pickupFound.getStatus(), "Partner")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Verify if the password is correct
        if (!new BCryptPasswordEncoder().matches(pickup.getPassword(), pickupFound.getPassword())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
}
