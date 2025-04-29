package com.formation.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Long formateurId; // ID du formateur associé au cours
    private String formateurUsername; // Nom d'utilisateur du formateur associé au cours
}
