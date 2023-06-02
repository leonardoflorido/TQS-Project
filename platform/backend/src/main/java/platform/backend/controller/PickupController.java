package platform.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import platform.backend.model.Pickup;
import platform.backend.pojo.PickupPOJO;
import platform.backend.record.Login;
import platform.backend.service.PickupService;

import java.util.List;

import static platform.backend.mapper.PickupMapper.mapPOJOToPickup;

@RestController
@RequestMapping("/pickup")
public class PickupController {
    private final PickupService pickupService;

    @Autowired
    public PickupController(PickupService pickupService) {
        this.pickupService = pickupService;
    }

    @PostMapping("/register")
    public ResponseEntity<Pickup> registerPickup(@Valid @RequestBody PickupPOJO pickupPOJO) {
        Pickup pickup = mapPOJOToPickup(pickupPOJO);
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
    public ResponseEntity<Pickup> updatePickupStatus(@Valid @RequestBody PickupPOJO pickupPOJO) {
        Pickup pickupFound = pickupService.findByEmail(pickupPOJO.getEmail());

        // Change the pickup's status
        pickupFound.setStatus(pickupPOJO.getStatus());

        // Update the pickup
        return new ResponseEntity<>(pickupService.save(pickupFound), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
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
    @CrossOrigin(origins = "*")
    @GetMapping("/get-partners")
    public ResponseEntity<List<Pickup>> getPartners() {
        List<Pickup> pickups = pickupService.findAllByStatus("Partner");

        // Verify if there are any pickups
        if (pickups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pickups, HttpStatus.OK);
    }
}
