package com.formation.platform.controller;

import com.formation.platform.model.User;
import com.formation.platform.repository.UserRepository;
import com.formation.platform.security.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Login ou première connexion (si l'utilisateur n'existe pas, il sera créé)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        // Rechercher l'utilisateur dans la base de données
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            // Si l'utilisateur n'existe pas, on le crée (inscription)
            user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword())); // Encodage du mot de passe
            user.setRole("USER"); // Vous pouvez définir un rôle par défaut ici (ex : "USER")
            userRepository.save(user);
        } else {
            // Si l'utilisateur existe déjà, vérifier le mot de passe
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).body("Nom d'utilisateur ou mot de passe incorrect");
            }
        }

        // Générer un JWT après la création ou la connexion de l'utilisateur
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        // Retourner le token et d'autres informations utiles
        return ResponseEntity.ok(new AuthResponse(token, user.getUsername(), user.getRole()));
    }

    // Classes internes pour request / response

    @Data
    static class AuthRequest {
        private String username;
        private String password;
    }

    @Data
    static class AuthResponse {
        private final String token;
        private final String username;
        private final String role;
    }
}
