package platform.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import platform.backend.exception.DetailsException;
import platform.backend.model.ACP;
import platform.backend.service.ACPService;

import java.util.List;

@RestController
@RequestMapping("/acp")
public class ACPController {
    private final ACPService acpService;

    @Autowired
    public ACPController(ACPService acpService) {
        this.acpService = acpService;
    }

    @PostMapping("/register")
    public ResponseEntity<ACP> register(@RequestBody ACP acp) throws DetailsException {
        // Verify if the email is already registered
        if (acpService.findByEmail(acp.getEmail()) != null) {
            throw new DetailsException("Email already registered");
        }

        // Encrypt the password
        acp.setPassword(new BCryptPasswordEncoder().encode(acp.getPassword()));

        // Save the admin
        return new ResponseEntity<>(acpService.save(acp), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ACP> login(@RequestBody ACP acp) throws DetailsException {
        ACP foundACP = acpService.findByEmail(acp.getEmail());

        // Verify if the acp exists
        if (foundACP == null) {
            throw new DetailsException("ACP does not exist");
        }

        // Verify if the status is partener
        if (!foundACP.getStatus().equals("partener")) {
            throw new DetailsException("ACP is not partener");
        }

        // Verify if the password is correct
        if (!new BCryptPasswordEncoder().matches(acp.getPassword(), foundACP.getPassword())) {
            throw new DetailsException("Incorrect password");
        }

        // Return the admin
        return new ResponseEntity<>(foundACP, HttpStatus.OK);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<ACP>> get_all() {
        return new ResponseEntity<>(acpService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ACP> get(@RequestParam Long id) {
        return new ResponseEntity<>(acpService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ACP> update(@RequestBody ACP acp) {
        return new ResponseEntity<>(acpService.save(acp), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        acpService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
