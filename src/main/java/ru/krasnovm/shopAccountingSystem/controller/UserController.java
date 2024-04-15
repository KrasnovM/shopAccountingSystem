package ru.krasnovm.shopAccountingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.krasnovm.shopAccountingSystem.entity.ShopUser;
import ru.krasnovm.shopAccountingSystem.repository.ShopUserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private ShopUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/me")
    public ResponseEntity<Optional<ShopUser>> getMyDetails() {
        UserDetails userDetails = getLoggedUserDetails();
        if (userDetails != null) {
            return ResponseEntity.ok(userRepository.findByName(userDetails.getUsername()));
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getUsersAdmin() {
        return userRepository.findAllUsers().toString();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> saveUser(@RequestBody ShopUser shopUser) {
        shopUser.setPassword(passwordEncoder.encode(shopUser.getPassword()));
        ShopUser result = userRepository.save(shopUser);

        if (result.getId() > 0) {
            return ResponseEntity.ok("User saved");
        }

        return ResponseEntity.badRequest().body("User not saved");
    }

    public UserDetails getLoggedUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
