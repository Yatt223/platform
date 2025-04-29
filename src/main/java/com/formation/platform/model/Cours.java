package com.formation.platform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cours")
@NoArgsConstructor
@AllArgsConstructor
public class Cours {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private User formateur;
   

    // Getters and Setters
}
