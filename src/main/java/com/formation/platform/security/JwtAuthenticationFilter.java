package com.formation.platform.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;  // Utilisation de JwtUtil pour extraire et valider les tokens

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        
        // Extraire le token de l'en-tête Authorization
        String header = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);  // Supprimer "Bearer " pour obtenir le token
            username = jwtUtil.extractUsername(token);  // Extraire le nom d'utilisateur du token
        }

        // Vérifier si le token est valide et si l'utilisateur n'est pas déjà authentifié
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(token, username)) {
                // Extraire les rôles du token (ici on suppose qu'ils sont stockés sous le nom "role")
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(jwtUtil.getSecretKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String role = (String) claims.get("role");  // Récupérer le rôle depuis les claims

                // Créer l'authentification avec le rôle et ajouter les détails de la requête
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(username, null, 
                        new ArrayList<>());  // Ajouter des authorities si nécessaire
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Passer à la suite du filtre
        filterChain.doFilter(request, response);
    }
}
