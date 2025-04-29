package com.formation.platform.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.formation.platform.dto.CoursDto;
import com.formation.platform.model.Cours;
import com.formation.platform.model.User;
import com.formation.platform.repository.UserRepository;

@Component
public class CoursMapper {

    @Autowired
    private UserRepository userRepository;

    public CoursDto toDto(Cours cours) {
        if (cours == null) {
            return null;
        }
        CoursDto coursDto = new CoursDto();
        coursDto.setId(cours.getId());
        coursDto.setTitle(cours.getTitle());
        coursDto.setDescription(cours.getDescription());
        coursDto.setImageUrl(cours.getImageUrl());
        
        if (cours.getFormateur() != null) {
            coursDto.setFormateurId(cours.getFormateur().getId());
            coursDto.setFormateurUsername(cours.getFormateur().getUsername());
        }
        
        return coursDto;
    }
    public Cours toEntity(CoursDto coursDto) {
        if (coursDto == null) {
            return null;
        }
        Cours cours = new Cours();
        cours.setId(coursDto.getId());
        cours.setTitle(coursDto.getTitle());
        cours.setDescription(coursDto.getDescription());
        cours.setImageUrl(coursDto.getImageUrl());
        
        if (coursDto.getFormateurId() != null) {
            User formateur = userRepository.findById(coursDto.getFormateurId()).orElse(null);
            cours.setFormateur(formateur);
        }
        
        return cours;
    }


}
