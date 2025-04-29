package com.formation.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.platform.dto.CoursDto;
import com.formation.platform.mapper.CoursMapper;
import com.formation.platform.repository.CoursRepository;

@Service
public class CoursService {
    @Autowired
    private CoursRepository coursRepository;
    private CoursMapper coursMapper;

    public List<CoursDto> getAll() {
        return coursRepository.findAll()
                .stream()
                .map(coursMapper::toDto)
                .toList();
    }
    public CoursDto create(CoursDto dto) {
        return coursMapper.toDto(coursRepository.save(coursMapper.toEntity(dto)));
    }

    public CoursDto getById(Long id) {
        return coursMapper.toDto(coursRepository.findById(id).orElseThrow());
    }
    public void delete(Long id) {
        coursRepository.deleteById(id);
    }

    
}
