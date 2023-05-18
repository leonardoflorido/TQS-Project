package platform.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import platform.backend.exception.DetailsException;
import platform.backend.model.Pickup;
import platform.backend.service.PickupService;

@RestController
@RequestMapping("/pickup")
public class PickupController {
    private final PickupService pickupService;

    @Autowired
    public PickupController(PickupService pickupService) {
        this.pickupService = pickupService;
    }

    @PostMapping("/register")
    public ResponseEntity<Pickup> registerPickup(@Valid @RequestBody Pickup pickup) throws DetailsException {
        // Verify if the pickup already exists
        if (pickupService.findByEmail(pickup.getEmail()) != null) {
            throw new DetailsException("Pickup already exists");
        }

        // Encrypt the password
        pickup.setPassword(new BCryptPasswordEncoder().encode(pickup.getPassword()));

        // Save the pickup
        return new ResponseEntity<>(pickupService.save(pickup), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Pickup> loginPickup(@Valid @RequestBody Pickup pickup) throws DetailsException {
        // Verify if the pickup exists
        Pickup pickupFound = pickupService.findByEmail(pickup.getEmail());
        if (pickupFound == null) {
            throw new DetailsException("Pickup does not exist");
        }

        // Verify if the password is correct
        if (!new BCryptPasswordEncoder().matches(pickup.getPassword(), pickupFound.getPassword())) {
            throw new DetailsException("Incorrect password");
        }

        // Return the pickup
        return new ResponseEntity<>(pickupFound, HttpStatus.OK);
    }
}
