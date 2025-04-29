package com.formation.platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.platform.model.Cours;

public interface CoursRepository extends JpaRepository<Cours, Long> {
    // Méthode pour trouver un cours par son titre
    Optional<Cours> findByTitle(String title);

    // Méthode pour vérifier si un cours existe par son titre
    Boolean existsByTitle(String title);

    // Méthode pour trouver tous les cours d'un formateur
    List<Cours> findByFormateurId(Long formateurId);
    
}
